package com.example.managment_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.managment_application.Adapter.CatAdapter;
import com.example.managment_application.DAO.CatDAO;
import com.example.managment_application.DTO.CatDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CatDAO catDAO;
    ArrayList<CatDTO> listCat;
    ListView lvCat;
    Button btnAdd, btnUpdate, btnDelete;
    EditText edCatName;
    CatAdapter adapter ;
    CatDTO objCurrentCat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Ánh xạ view
        lvCat = findViewById(R.id.lv_cat);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        edCatName = findViewById(R.id.ed_catname);

        // Khởi tạo DAO
        catDAO = new CatDAO(this);
        listCat = catDAO.getList();
        adapter = new CatAdapter(this,listCat);
        lvCat.setAdapter( adapter );

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // có thể viết tách riêng code này thành hàm riêng
                //1. Lấy dữ liệu người dùng nhập trên edittext
                String catName = edCatName.getText().toString();
                // kiểm tra hợp lệ dữ liệu truowcs khi thêm, VD:
                if(catName.length()<3){
                    Toast.makeText(MainActivity.this,
                            "Tên cân nhập ít nhất 3 ký tu", Toast.LENGTH_SHORT).show();
                    return; // thoat khoi hàm
                }




                CatDTO objCat = new CatDTO();
                objCat.setName( catName );
                //2. Ghi vao CSDL
                int res = catDAO.insert(String.valueOf(objCat)); // ghi vào CSDL
                //3. Cập nhật ds
                if(res>0){ // Ghi thành công
                    listCat.clear(); // xóa hết
                    listCat.addAll( catDAO.getList());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Lỗi thêm, hãy kiểm tra trùng lặp...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}