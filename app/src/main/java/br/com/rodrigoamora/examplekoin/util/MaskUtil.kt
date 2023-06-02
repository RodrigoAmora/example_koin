package br.com.rodrigoamora.examplekoin.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


object MaskUtil {

    const val FORMAT_PHONE = "(##)#####-####"

    var old = ""
    var isUpdating = false

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param editText
     * @param mask
     * @return
     */
    fun addMaskInEditText(editText: EditText, mask: String): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                val str = unmask(s.toString())

                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                val mascara = addMask(s, mask)

                isUpdating = true

                editText.setText(mascara)
                mascara?.let { editText.setSelection(it.length) }
            }
        }
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
            .replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
    }

    fun addMask(s: CharSequence, mask: String): String? {
        val str = unmask(s.toString())
        var mascara = ""

        if (isUpdating) {
            old = str
            isUpdating = false
            return old
        }

        var i = 0
        for (m in mask.toCharArray()) {
            if (m != '#' && str.length > old.length) {
                mascara += m
                continue
            }
            mascara += try {
                str[i]
            } catch (e: Exception) {
                break
            }
            i++
        }

        return mascara
    }

}