package com.matheus0liveira.rickandmorty.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.Info
import com.matheus0liveira.rickandmorty.presenter.CharacterPresenter
import com.matheus0liveira.rickandmorty.presenter.CharacterView


class HomeFragment : Fragment(R.layout.home_fragment), CharacterView,
    SearchView.OnQueryTextListener {

    private lateinit var presenter: CharacterPresenter
    private lateinit var adapter: HomeAdapter
    private val characters: MutableList<Character> = mutableListOf()
    private lateinit var progressBar: FrameLayout
    private lateinit var searchText: EditText
    private lateinit var prevBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var txtCurrentPage: TextView
    private lateinit var footer: LinearLayout
    private lateinit var searchView: SearchView
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        footer = view.findViewById(R.id.footer)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                searchView = menu.findItem(R.id.action_search)?.actionView as SearchView

                searchText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
                searchView.setOnQueryTextListener(this@HomeFragment);

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> true
                    R.id.action_refresh -> true
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        prevBtn = view.findViewById(R.id.prev_btn)
        nextBtn = view.findViewById(R.id.next_btn)
        progressBar = view.findViewById(R.id.progress_overlay)
        txtCurrentPage = view.findViewById(R.id.txt_current_page)


        presenter = CharacterPresenter(this)

        if (characters.size == 0) presenter.findAllCharacters(currentPage)

        adapter = HomeAdapter(characters) {

            val bundle = Bundle()
            bundle.putInt(CharacterFragment.ID_KEY, it.id)

            val navController = findNavController()

            navController.navigate(R.id.action_nav_home_to_nav_character, bundle)
        }


        val rv = view.findViewById<RecyclerView>(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
    }


    private fun closeSearchViewOnToolbar() {
        searchView.onActionViewCollapsed();

    }

    private fun closeKeyboard() {

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun showCharacter(character: List<Character>) {
        characters.clear()
        characters.addAll(character)
        adapter.notifyDataSetChanged()
    }

    override fun handleInfo(info: Info) {
        footer.visibility = View.VISIBLE
        txtCurrentPage.text = currentPage.toString()
        prevBtn.setOnClickListener {
            --currentPage
            presenter.findAllCharacters(currentPage)
        }
        nextBtn.setOnClickListener {
            ++currentPage
            presenter.findAllCharacters(currentPage)
        }

        prevBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))

        if (info.prev == null) {
            prevBtn.isEnabled = false
            prevBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))
        } else {
            prevBtn.isEnabled = true
            prevBtn.setTextColor(resources.getColor(R.color.dark_blue, resources.newTheme()))
        }
        if (info.next == null) {
            nextBtn.isEnabled = false
            nextBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))
        } else {
            nextBtn.isEnabled = true
            nextBtn.setTextColor(resources.getColor(R.color.dark_blue, resources.newTheme()))
        }


    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        closeKeyboard()
        closeSearchViewOnToolbar()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.findAllCharacters(currentPage, query)

        return true
    }

    override fun onQueryTextChange(newText: String?) = true
}