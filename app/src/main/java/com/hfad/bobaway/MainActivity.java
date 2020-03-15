//package com.hfad.bobaway;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
////
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//    private TextView mBobashopListTextView;
//    private EditText mBobashopEntryEditText;
//
//    private ArrayList<String> mBobashopList;
//
//    private RecyclerView mBobawayListRecyclerView;
//    private BobawayAdapter mBobawayAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        TextView bobashopListTextView = (TextView) findViewById(R.id.tv_bobashop_list);
//
//        mBobashopListTextView =
//                (TextView)findViewById(R.id.tv_bobashop_list);
//        mBobashopEntryEditText =
//                (EditText)findViewById(R.id.et_bobashop_entry_box);
//
//        Button searchBobashopButton =
//                (Button)findViewById(R.id.btn_bobashop_search);
//
//        mBobawayListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mBobawayListRecyclerView.setHasFixedSize(true);
//
//        mBobawayAdapter = new BobawayAdapter();
//        mBobawayListRecyclerView.setAdapter(mBobawayAdapter);
//
//        mBobashopList = new ArrayList<String>();
//
//        searchBobashopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bobashopText =
//                        mBobashopEntryEditText.getText().toString();
//                if (!TextUtils.isEmpty(bobashopText)) {
//                    mBobashopListTextView.setText(bobashopText);
//                    mBobashopEntryEditText.setText("");
//                }
//            }
//        });
//    }
//
//
//}
//

package com.hfad.bobaway;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

}