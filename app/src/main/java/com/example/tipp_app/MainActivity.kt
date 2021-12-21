package com.example.tipp_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipp_app.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding //biz yaptık

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)//biz yaptık
        setContentView(binding.root) //biz yaptık

        binding.hesaplaButton.setOnClickListener{  //hesapla butonuna tıklayınca fonk calısır
            bahsisHesapla()
        }



    }


    fun bahsisHesapla() {

        val hizmetBedeliString = binding.hizmetBedeliEditText.text.toString()

        val tutar = hizmetBedeliString.toDoubleOrNull()

        if (tutar == null) {  // tutar bolumu bos bırakılırsa dıye kontrol
            binding.bahsisTutariTextView.text = ""
            return
        }

        val secilenRadioButton = binding.bahsisSecenekleriRadioGroup.checkedRadioButtonId  // secilen radio butonunu tutar

        val bahsisYuzdesi = when (secilenRadioButton) { // radio butonu kontrol
            R.id.yuzde_yirmi_radio_button -> 0.20
            R.id.yuzde_onsekiz_radio_button -> 0.18
            else -> 0.15
        }

        var bahsis = bahsisYuzdesi * tutar

        val yukariYuvarla = binding.bahsisYukariYuvarlaSwitch.isChecked  // swicht acık sa 1 olur

        if (yukariYuvarla) {  //yukariYuvarla 1 ise şart saglanır
            bahsis = kotlin.math.ceil(bahsis) // yuvarlamak ıcın mat fonksıyonu
        }

        val formatlananBahsis = NumberFormat.getCurrencyInstance(Locale("tr", "TR")).format(bahsis)
        // yukarıda kı kod ₺ ve vırgulden sonra 2 rakamı yazdıramya yarar
        binding.bahsisTutariTextView.text = getString(R.string.bahsis_tutari, formatlananBahsis)

    }
}