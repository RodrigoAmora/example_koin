package br.com.rodrigoamora.examplekoin.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.util.PackageInfoUtil

class AboutActivity : AppCompatActivity() {

    private lateinit var tvVersion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        createToolbar()
        initViews()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initViews() {
        tvVersion = findViewById(R.id.tv_version_app)
        tvVersion.text = getString(R.string.version_app,
                                    PackageInfoUtil.getVersionName(this))
    }

}
