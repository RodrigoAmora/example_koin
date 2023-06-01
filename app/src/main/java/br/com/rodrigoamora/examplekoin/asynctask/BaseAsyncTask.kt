package br.com.rodrigoamora.examplekoin.asynctask

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val whenExecutes: () -> T,
    private val whenEnds: (result: T) -> Unit
) : AsyncTask<Void, Void, T>() {

    override fun doInBackground(vararg params: Void?) = whenExecutes()

    override fun onPostExecute(result: T) {
        super.onPostExecute(result)
        whenEnds(result)
    }

}
