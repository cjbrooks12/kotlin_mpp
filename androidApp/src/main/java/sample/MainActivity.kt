package sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import sample.presentation.MainPresenter
import sample.presentation.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        hello_world.setOnClickListener { mainPresenter.searchWeather(et_input.text.toString()) }
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onStart()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onFinish()
    }

    override fun setButtonText(text: String) = runOnUiThread {
        hello_world.text = text
    }

    override fun setTextContent(text: String) = runOnUiThread {
        text_content.text = text
    }

    override fun showError(message: String) = runOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
