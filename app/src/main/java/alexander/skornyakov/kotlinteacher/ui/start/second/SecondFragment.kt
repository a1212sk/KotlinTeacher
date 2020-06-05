package alexander.skornyakov.kotlinteacher.ui.start.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.databinding.StartSecondFragmentBinding
import alexander.skornyakov.kotlinteacher.ui.main.MainActivity
import alexander.skornyakov.kotlinteacher.ui.start.StartActivity
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() =
            SecondFragment()
    }

    @Inject lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<StartSecondFragmentBinding>(
            inflater,
            R.layout.start_second_fragment,
            container,
            false)
        binding.vm = viewModel
        binding.button.setOnClickListener {
            Intent(context, MainActivity::class.java).let {
                startActivity(it)
                activity?.finish()
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as StartActivity).startComponent.inject(this)
        viewModel = viewModelProviderFactory.create(SecondViewModel::class.java)
    }
}
