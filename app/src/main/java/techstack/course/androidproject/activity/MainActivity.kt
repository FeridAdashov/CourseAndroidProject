//package techstack.course.androidproject
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.ListView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.loader.app.LoaderManager
//import com.bumptech.glide.Glide
//import techstack.course.androidproject.databinding.ActivityMainBinding
//
//
//data class DataModel(val name: String, val phone: String, val image: String)
//
//
//class CustomAdapter(data: ArrayList<DataModel>, context: Context) :
//    ArrayAdapter<DataModel>(context, R.layout.contact_list_item, data) {
//
//    override fun getView(position: Int, v: View?, parent: ViewGroup): View {
//        var convertView = v
//        val dataModel: DataModel? = getItem(position)
//
//        if (convertView == null) {
//            val inflater = LayoutInflater.from(context)
//            convertView = inflater.inflate(R.layout.contact_list_item, parent, false)
//        }
//
//        convertView!!.findViewById<TextView?>(R.id.text1)?.text = dataModel?.name
//        convertView.findViewById<TextView?>(R.id.text2)?.text = dataModel?.phone
//
//        convertView.findViewById<ImageView?>(R.id.imageView)?.apply {
//            Glide.with(context).load(dataModel?.image ?: "").into(this)
//        }
//
//        return convertView
//    }
//}
//
//
//class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
//
//    private lateinit var binding: ActivityMainBinding
//
//    private lateinit var contactsListView: ListView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        contactsListView = binding.listViewContacts
//
//        // Sets the adapter for the ListView
//        contactsListView.onItemClickListener = this
//
//        loadContacts()
//    }
//
//    private fun loadContacts() {
//        LoaderManager.getInstance(this@MainActivity)
//            .initLoader(0, null, ContactsLoader(this@MainActivity) { contacts ->
//                contactsListView.adapter = CustomAdapter(contacts, this@MainActivity)
//            })
//    }
//
//    override fun onItemClick(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        Log.d("SSSSSS", "Pos: $p2")
//    }
//}
//
///*

package techstack.course.androidproject.activity

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import techstack.course.androidproject.ContactsLoader
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.ActivityMainBinding


data class DataModel(val name: String, val phone: String)


class CustomAdapter(data: ArrayList<DataModel>, context: Context) :
    ArrayAdapter<DataModel>(context, R.layout.contact_list_item, data) {

    override fun getView(position: Int, v: View?, parent: ViewGroup): View {
        var convertView = v
        val dataModel: DataModel? = getItem(position)

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false)
        }

        (convertView?.findViewById<View>(R.id.text1) as? TextView?)?.text = dataModel?.name
        (convertView?.findViewById<View>(R.id.text2) as? TextView?)?.text = dataModel?.phone

        return convertView!!
    }
}


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding


    private lateinit var contactsList: ListView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                loadContacts()
            } else {
                Toast.makeText(this@MainActivity, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        contactsList = binding.listViewContacts

        // Sets the adapter for the ListView
        contactsList.onItemClickListener = this


        Log.d(
            "SSSSSSSS",
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_CONTACTS
            ).toString()
        )

        Log.d(
            "SSSSSSSS",
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ).toString()
        )

        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadContacts()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) -> {
                AlertDialog.Builder(this)
                    .setTitle("Contacts permission")
                    .setMessage("Give permission for contacts, Pleaseeeeee))")
                    .setPositiveButton("Accept") { _, _ ->
                        requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                    }
                    .setNegativeButton("Deny") { _, _ -> }
                    .show()
            }

            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun loadContacts() {
        LoaderManager.getInstance(this@MainActivity)
            .initLoader(0, null, ContactsLoader(this@MainActivity) { contacts ->
                contactsList.adapter = CustomAdapter(contacts, this@MainActivity)
            })
    }

    override fun onItemClick(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.d("SSSSSS", "Pos: $p2")
    }
}
