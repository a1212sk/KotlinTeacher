package alexander.skornyakov.kotlinteacher.ui.main.second

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.repository.IRepository
import alexander.skornyakov.kotlinteacher.databinding.MainSecondFragmentBinding
import alexander.skornyakov.kotlinteacher.ui.main.MainActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondFragment : Fragment() {

    lateinit var vm: SecondViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var repository: IRepository

    lateinit var loadDataJob: Job

    lateinit var binding: MainSecondFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<MainSecondFragmentBinding>(
            inflater,
            R.layout.main_second_fragment,
            container,
            false
        )
        binding.vm = vm
        vm.chapter.observe(viewLifecycleOwner, Observer {
            it?.let {
                (activity as MainActivity).setTitle(it.toString())
            }
        })
        initRecyclerView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initData(binding)
    }

    private fun initData(binding: MainSecondFragmentBinding) {
        val items = binding.vm?.items
        items?.value?.clear()
        loadDataJob = CoroutineScope(Dispatchers.IO + Job()).launch {
            repository.getAllStepModels()
                .flowOn(Dispatchers.IO)
                .collect {
                    items?.value?.add(it)
                    items?.postValue(items?.value)
                }
        }
    }

    private fun initRecyclerView(binding: MainSecondFragmentBinding) {
        val rvAdapter = SecondRecyclerViewAdapter()
        binding.rv.adapter = rvAdapter
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.vm?.items?.removeObservers(viewLifecycleOwner)
        binding.vm?.items?.observe(viewLifecycleOwner, Observer {
            rvAdapter.submitList(it.toMutableList())
        })
        rvAdapter.setOnItemClickListener(object : SecondRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {

            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
        vm = viewModelProviderFactory.create(SecondViewModel::class.java)
        val chapter = arguments?.get("chapterNumber") as Int
        vm.setChapter(chapter)
    }
}