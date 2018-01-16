package com.example.atmc.techknowlogy_l;

/**
 * Created by ATMC on 02-05-2017.
 */

interface Callback2
{
    void handleResponse(String err, String response);
}

public class GetRequestG
{

    private String link = "https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=37ad4ba874db4605aa492c25a237776f";

    public void get(final Callback2 caller)
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