package com.matheus0liveira.rickandmorty.view

import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.matheus0liveira.rickandmorty.R

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchText: EditText
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar = findViewById<Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_about
            ), drawerLayout
        )



        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)

        navView.setupWithNavController(navHostFragment.navController)


//        presenter = CharacterPresenter(this)
//        progressBar = findViewById(R.id.progress_overlay)
//
//        characters = mutableListOf()
//        presenter.findAllCharacters(currentPage)
//
//        adapter = MainAdapter(characters)
//        val rv = findViewById<RecyclerView>(R.id.rv_main)
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = adapter

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(getColor(R.color.off_white))

        searchView.setOnQueryTextListener(this);

        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.action_refresh -> {
//                if (currentPage == 1) return true
//                presenter.findAllCharacters(1, "")
//                return true
//            }
//
//        }
//        return super.onOptionsItemSelected(item)
//    }


    //    inner class MainAdapter(private val items: List<Character>) :
//        RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//            val view = layoutInflater.inflate(R.layout.character_item, parent, false)
//            return MainViewHolder(view)
//        }
//
//        override fun getItemCount() = items.size
//        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//            holder.bind(items[position])
//        }
//
//        inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//            fun bind(character: Character) {
//
//                val txtName = itemView.findViewById<TextView>(R.id.character_name)
//                val txtOrigin = itemView.findViewById<TextView>(R.id.character_origin)
//                val txtSpecie = itemView.findViewById<TextView>(R.id.character_specie)
//                val txtStatus = itemView.findViewById<TextView>(R.id.character_status)
//                val imgView = itemView.findViewById<ImageView>(R.id.character_img)
//                imgView.clipToOutline = true
//                Picasso.get().load(character.imgUrl).into(imgView);
//                txtName.text = character.name
//                txtSpecie.text = getString(R.string.specie, character.specie)
//                txtOrigin.text = getString(R.string.location, character.origin)
//                txtStatus.text = getString(R.string.status, character.status)
//
//            }
//
//        }
//
//    }
//
//    override fun showCharacter(character: List<Character>) {
//        characters.clear()
//        characters.addAll(character)
//        adapter.notifyDataSetChanged()
//    }
//
//    override fun handleInfo(info: Info) {
//
//        val prevBtn = findViewById<Button>(R.id.prev_btn)
//        val nextBtn = findViewById<Button>(R.id.next_btn)
//        val txtCurrentPage = findViewById<TextView>(R.id.txt_current_page)
//        txtCurrentPage.text = currentPage.toString()
//        prevBtn.setOnClickListener {
//            --currentPage
//            presenter.findAllCharacters(currentPage)
//        }
//        nextBtn.setOnClickListener {
//            ++currentPage
//            presenter.findAllCharacters(currentPage)
//        }
//
//        prevBtn.setTextColor(resources.getColor(R.color.gray, theme))
//
//        if (info.prev == null) {
//            prevBtn.isEnabled = false
//            prevBtn.setTextColor(resources.getColor(R.color.gray, theme))
//        } else {
//            prevBtn.isEnabled = true
//            prevBtn.setTextColor(resources.getColor(R.color.dark_blue, theme))
//        }
//        if (info.next == null) {
//            nextBtn.isEnabled = false
//            nextBtn.setTextColor(resources.getColor(R.color.gray, theme))
//        } else {
//            nextBtn.isEnabled = true
//            nextBtn.setTextColor(resources.getColor(R.color.dark_blue, theme))
//        }
//
//
//    }
//
//    override fun showProgress() {
//        progressBar.visibility = View.VISIBLE
//    }
//
//    override fun hideProgress() {
//        progressBar.visibility = View.GONE
//    }
//
//    override fun showError(message: String) {
//        TODO("Not yet implemented")
//    }
//
    override fun onQueryTextSubmit(query: String?): Boolean {
//        presenter.findAllCharacters(currentPage, query)
//
//        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
//            .hideSoftInputFromWindow(searchText.windowToken, 0)

        return true
    }

    override fun onQueryTextChange(newText: String?) = true
}

