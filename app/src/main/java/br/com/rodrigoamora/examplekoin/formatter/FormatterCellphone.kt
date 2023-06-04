package br.com.rodrigoamora.examplekoin.formatter

class FormatterCellphone {

    companion object {
        fun format(cellphoneWithAreaCode: String): String? {
            return cellphoneWithAreaCode
                .replace("([0-9]{2})([0-9]{4,5})([0-9]{4})".toRegex(), "($1) $2-$3")
        }

        fun remove(cellphoneWithAreaCode: String): String? {
            return cellphoneWithAreaCode
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "")
        }
    }

}