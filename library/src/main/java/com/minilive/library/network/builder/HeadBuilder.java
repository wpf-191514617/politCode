package com.minilive.library.network.builder;


import com.minilive.library.network.OkHttpUtils;
import com.minilive.library.network.request.OtherRequest;
import com.minilive.library.network.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
