package com.example.khabrein.ui.gallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.khabrein.*
import kotlinx.android.synthetic.main.fragment_home.*

class GalleryFragment : Fragment(),NewsitemClicked {
    private lateinit var mAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).settoolbar("Khabrein")

        recyclerview.layoutManager = LinearLayoutManager(activity)
        fetcdata()
        mAdapter = NewsAdapter(this)
        recyclerview.adapter = mAdapter

    }

    private fun fetcdata(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=120fead7bbb04a779388c1e623943c85"
        val  jsonObjectRequest = object: JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener {
                val newsJasonArray = it.getJSONArray("articles")
                val newsarray = ArrayList<newsdata>()
                for (i in 0 until newsJasonArray.length()) {
                    val newsjasonobj = newsJasonArray.getJSONObject(i)
                    val news = newsdata(
                        newsjasonobj.getString("title"),
                        newsjasonobj.getString("author"),
                        newsjasonobj.getString("url"),
                        newsjasonobj.getString("urlToImage")
                    )
                    newsarray.add(news)
                }

                mAdapter.updatenews(newsarray)
            },
            Response.ErrorListener{

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(requireContext()).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: newsdata) {
        val intent = Intent(activity, webviewacticity::class.java)
        intent.putExtra("value",item.url)
        startActivity(intent)
    }
}