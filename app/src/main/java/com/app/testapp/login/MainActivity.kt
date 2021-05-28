package com.app.testapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testapp.R
import com.app.testapp.adapter.MyAdapter
import com.app.testapp.model.datamodel.ChildData
import com.app.testapp.model.datamodel.DataItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listMain=ArrayList<DataItem>();

    private var mViewModel: LoginViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //reference of login model
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        attachObservers()

        initializeList()

    }


    //Initializing more section list
    private fun initializeList(){


        try{
            val listSettings=ArrayList<ChildData>();
            val listLegal=ArrayList<ChildData>();
            val listContact=ArrayList<ChildData>();


            listSettings.add(ChildData(getString(R.string.language), R.drawable.language,false))
            listSettings.add(ChildData(getString(R.string.translation), R.drawable.translation,false))


            listLegal.add(ChildData(getString(R.string.about_us), R.drawable.about_us,false));
            listLegal.add(ChildData(getString(R.string.terms_and_conditions), R.drawable.terms,false))



            listContact.add(ChildData(getString(R.string.agents), R.drawable.agnets,false));
            listContact.add(ChildData(getString(R.string.supports), R.drawable.support,false));


            listMain.add(DataItem(getString(R.string.settings), R.drawable.settings,false,listSettings));
            listMain.add(DataItem(getString(R.string.legal), R.drawable.legal,false,listLegal));
            listMain.add(DataItem(getString(R.string.contact_us), R.drawable.contact,false,listContact));


            var  mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerMains?.layoutManager = mLayoutManager
            var mAdapter = MyAdapter(this, listMain)
            recyclerMains?.adapter = mAdapter
        }
        catch (e:Exception){

        }
    }


    /**
     *Attach observer to model, if any change occur will be observed here and response is updated on ui
     */
    private fun attachObservers() {

        mViewModel?.response?.observe(this, Observer {

        })
        //If any api error will occur this will be observed
        mViewModel?.mApiError?.observe(this, Observer {

        })
        //this will observe the loader
        mViewModel?.isLoading?.observe(this, Observer {


        })
        //this will show message if any failure will occur
        mViewModel?.onFailure?.observe(this, Observer {

        })

    }


}