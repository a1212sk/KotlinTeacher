package alexander.skornyakov.kotlinteacher.ui.main.first

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.repository.IRepository
import alexander.skornyakov.kotlinteacher.databinding.MainFirstFragmentBinding
import alexander.skornyakov.kotlinteacher.ui.main.MainActivity
import alexander.skornyakov.kotlinteacher.ui.main.first.FirstRecyclerViewAdapter.OnItemClickListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FirstFragment : Fragment(){

    lateinit var vm: FirstViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    @Inject
    lateinit var repository: IRepository

    lateinit var loadDataJob: Job

    lateinit var binding: MainFirstFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<MainFirstFragmentBinding>(
            inflater,
            R.layout.main_first_fragment,
            container,
            false
        )
        binding.vm = vm
        initRecyclerView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initData(binding)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
        vm = viewModelProviderFactory.create(FirstViewModel::class.java)
    }

    private fun initData(binding: MainFirstFragmentBinding){
        val items = binding.vm?.items
        items?.value?.clear()
        loadDataJob = CoroutineScope(Dispatchers.IO + Job()).launch {
            repository.getAllSections()
                .flowOn(Dispatchers.IO)
                .collect {
                    items?.value?.add(it)
                    items?.postValue(items?.value)
                }
        }
    }

    private fun initRecyclerView(binding: MainFirstFragmentBinding) {
        val rvAdapter = FirstRecyclerViewAdapter()
        binding.rv.adapter = rvAdapter
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(context)
        val items = binding.vm?.items
        items?.removeObservers(viewLifecycleOwner)
        items?.observe(viewLifecycleOwner, Observer {
            rvAdapter.submitList(it.toMutableList())
        })
        rvAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, chapterId: String) {
                loadDataJob.cancel()
                val action =
                    FirstFragmentDirections.actionMainFirstFragmentToMainSecondFragment(chapterId)
                findNavController().navigate(action)

            }
        })
    }

}