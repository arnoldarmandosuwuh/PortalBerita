package com.charisma.portalberita;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.charisma.portalberita.face.Listener;
import com.charisma.portalberita.model.News;
import com.charisma.portalberita.model.Response;
import com.charisma.portalberita.utils.NewsAdapter;
import com.charisma.portalberita.utils.WebService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MyNewsFragment extends Fragment implements Listener {
    private ListView lvNews;
    private List<News> newsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.nav_my_news));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_all_news);
        View view = inflater.inflate(R.layout.content_main, container, false);

        newsList = new ArrayList<>();
        lvNews = view.findViewById(R.id.lvNews);

        WebService service = new WebService(getActivity(), this, "ambil_berita.php", WebService.METHOD_GET);
        service.execute("authorId=" + ((MainActivity) getActivity()).authorId);

        return view;
    }

    @Override
    public void onSucessWs(Object[] objects) {
        Response response = (Response) objects[0];

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try {
            newsList = mapper.readValue(String.valueOf(response.getData()), new TypeReference<List<News>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        NewsAdapter adapter = new NewsAdapter(getActivity(), R.layout.adapter_news, newsList);
        lvNews.setAdapter(adapter);
    }

    @Override
    public void onFailedWs(Object[] objects) {
        Response response = (Response) objects[0];
        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
    }

}
