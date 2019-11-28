package io.ashdavies.playground.conferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.ashdavies.playground.R
import io.ashdavies.playground.binding
import io.ashdavies.playground.common.MainViewModel
import io.ashdavies.playground.databinding.RepoFragmentBinding
import io.ashdavies.playground.models.Repo
import kotlinx.coroutines.FlowPreview

@FlowPreview
internal class ConferencesFragment : Fragment() {

  private val model: ConferencesViewModel by viewModels { ConferencesViewModel.Factory(requireContext()) }
  private val parent: MainViewModel by viewModels()

  private val adapter: ConferencesAdapter<Repo> = ConferencesAdapter(R.layout.list_item)

  private lateinit var binding: RepoFragmentBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = inflater.binding(R.layout.repo_fragment, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    binding.model = model
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding
        .recycler
        .adapter = adapter

    with(model) {
      items.observe(viewLifecycleOwner, Observer(adapter::submitList))
      errors.observe(viewLifecycleOwner, Observer(parent::onError))
    }
  }
}
