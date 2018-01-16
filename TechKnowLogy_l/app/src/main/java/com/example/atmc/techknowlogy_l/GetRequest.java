package com.example.atmc.techknowlogy_l;

import org.json.JSONObject;

/**
 * Created by ATMC
 */

interface Callback
{
    void handleResponse(String err, String response);
}

public class GetRequest
{

    private String link = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=37ad4ba874db4605aa492c25a237776f";

    public void get(final Callback caller)
    {
        asyncTaskRunner asyncTask = new asyncTaskRunner();

        asyncTask.setListener(new asyncTaskRunner.Listener() {
            @Override
            public void onResult(String result) {
                if(result == null)
                    caller.handleResponse("NoInternetConnection", null);
                else
                    caller.handleResponse(null, result);
            }
        });

        String reqUrl = link;

        asyncTask.execute(reqUrl);
    }

}


