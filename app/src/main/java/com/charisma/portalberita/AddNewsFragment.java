package com.charisma.portalberita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charisma.portalberita.face.Listener;
import com.charisma.portalberita.model.Response;
import com.charisma.portalberita.utils.WebService;

/**
 * Created by Bayu Charisma Putra on 2/22/2019.
 */
public class AddNewsFragment extends Fragment implements Listener {

    private EditText etTitle, etContent, etPicture;
    private Button bNext;

    private int id;
    private String title, content, picture;
    private int authorId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Berita Baru");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_news, container, false);
        etTitle = view.findViewById(R.id.etTitle);
        etContent = view.findViewById(R.id.etContent);
        etPicture = view.findViewById(R.id.etPicture);
        bNext = view.findViewById(R.id.bNext);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                content = etContent.getText().toString();
                picture = etPicture.getText().toString();

                if (isValidInput()) {

                    WebService service = new WebService(getActivity(),
                            AddNewsFragment.this, "berita_baru.php",
                            WebService.METHOD_POST);
                    service.execute("authorId=" + authorId + "&" +
                            "title=" + title + "&" +
                            "content=" + content + "&" +
                            "picture=" + picture);

                }
            }
        });

        return view;
    }


    @Override
    public void onSucessWs(Object[] objects) {
        Response response = (Response) objects[0];
        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailedWs(Object[] objects) {
        Response response = (Response) objects[0];
        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
    }

    private boolean isValidInput() {
        if (title.length() < 1 ) {
            Toast.makeText(getActivity(), "Judul belum diisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (content.length() < 10 ) {
            Toast.makeText(getActivity(), "Konten minimal 10 karakter", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
