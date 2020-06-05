package alexander.skornyakov.kotlinteacher.ui.start.first

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.databinding.StartFirstFragmentBinding
import alexander.skornyakov.kotlinteacher.ui.start.StartActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import javax.inject.Inject

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() =
            FirstFragment()
    }

    lateinit var viewModel: FirstViewModel
    @Inject lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<StartFirstFragmentBinding>(
            inflater,
            R.layout.start_first_fragment,
            container,
            false)
        binding.vm = viewModel
        binding.goToSecondBtn.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.nav_controller)
                .navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as StartActivity).startComponent.inject(this)
        viewModel = viewModelProviderFactory.create(FirstViewModel::class.java)
    }
}
