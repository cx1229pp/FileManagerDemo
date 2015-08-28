package demo.progressdialog.com.filemanagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomAdapter myAdapter;
    private List<String> fileNameList;
    private List<String> pathList;
    private List<File> fileList = new ArrayList<File>();
    private String rootPath = "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        getFileList(rootPath);

        recyclerView = (RecyclerView)findViewById(R.id.rv_fileList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new CustomAdapter(this);
        myAdapter.setFileList(fileList);
        myAdapter.setOnItemClickLitener(new CustomAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View v, int pos, File file) {
                getFileList(file.getPath());
                myAdapter.setFileList(fileList);
                myAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(myAdapter);
    }

    private void getFileList(String path){
        Log.d("MainActivity","path:"+path);
        fileList.clear();
        File[] files = new File(path).listFiles();
        if(files != null && files.length > 0){
            Log.d("MainActivity","files-length:"+files.length);
            for(File file : files){
                fileList.add(file);
            }
        }
    }
}
