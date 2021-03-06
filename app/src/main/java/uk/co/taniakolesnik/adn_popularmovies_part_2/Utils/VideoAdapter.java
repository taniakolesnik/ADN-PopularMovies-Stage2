package uk.co.taniakolesnik.adn_popularmovies_part_2.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.co.taniakolesnik.adn_popularmovies_part_2.MainActivity;
import uk.co.taniakolesnik.adn_popularmovies_part_2.R;
import uk.co.taniakolesnik.adn_popularmovies_part_2.Video;

/**
 * Created by tetianakolesnik on 28/07/2018.
 */


public class VideoAdapter extends ArrayAdapter<Video> {

    private static final String VIDEO_URL_BASE = "https://www.youtube.com/watch?v=";

    Context context;
    List<Video> videos;

    public VideoAdapter(Context context, List<Video> videos) {
        super(context, 0, videos);
        this.context = context;
        this.videos = videos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Video video = getItem(position);
        final String videoUrl = VIDEO_URL_BASE + video.getVideoKey();
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.video_list_item, parent, false);
        }
        TextView name = convertView.findViewById(R.id.videoName_tv);
        name.setText(video.getVideoName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                PackageManager packageManager = context.getPackageManager();
                List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, 0);
                if (resolveInfo.size() > 0) {
                    intent.setData(Uri.parse(videoUrl));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.no_application_to_play_video),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return convertView;
    }

}