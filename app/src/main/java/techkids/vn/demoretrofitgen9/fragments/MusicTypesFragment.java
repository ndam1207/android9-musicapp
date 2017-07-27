package techkids.vn.demoretrofitgen9.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.demoretrofitgen9.R;
import techkids.vn.demoretrofitgen9.adapters.MusicTypeAdapter;
import techkids.vn.demoretrofitgen9.databases.MusicTypeModel;
import techkids.vn.demoretrofitgen9.events.OnClickMusicType;
import techkids.vn.demoretrofitgen9.managers.ScreenManager;
import techkids.vn.demoretrofitgen9.networks.GetMusicTypes;
import techkids.vn.demoretrofitgen9.networks.RetrofitFactory;
import techkids.vn.demoretrofitgen9.networks.json_models.music_types.AllMusicTypesJSONModel;
import techkids.vn.demoretrofitgen9.networks.json_models.music_types.MusicTypeJSONModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypesFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.rv_music_types)
    RecyclerView rvMusicTypes;

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    private MusicTypeAdapter musicTypeAdapter;

    public MusicTypesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_types, container, false);

        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        GetMusicTypes getMusicTypes = RetrofitFactory
                .getInstance().create(GetMusicTypes.class);
        getMusicTypes.getMusicTypes().enqueue(new Callback<AllMusicTypesJSONModel>() {
            @Override
            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
                for (MusicTypeJSONModel musicTypeJSONModel : response.body().getSubgenres()) {
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.setId(musicTypeJSONModel.getId());
                    musicTypeModel.setKey(musicTypeJSONModel.getTranslation_key());
                    musicTypeModel.setImageID(getContext().getResources().getIdentifier
                            ("genre_x2_" + musicTypeJSONModel.getId(), "raw", getContext().getPackageName()));
                    musicTypeModelList.add(musicTypeModel);
                }

                musicTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList, getContext());
        rvMusicTypes.setAdapter(musicTypeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager
                (getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });

        rvMusicTypes.setLayoutManager(gridLayoutManager);
        musicTypeAdapter.setOnItemClick(this);
    }

    @Override
    public void onClick(View v) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) v.getTag();

        EventBus.getDefault().postSticky(new OnClickMusicType(musicTypeModel));

        ScreenManager.openFragment(getActivity().getSupportFragmentManager(),
                new TopSongFragment(), R.id.layout_container);
    }
}
