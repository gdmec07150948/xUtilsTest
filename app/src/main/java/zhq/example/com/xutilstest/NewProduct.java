package zhq.example.com.xutilstest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import zhq.example.com.xutilstest.adapter.MyAdapter;

/**
 * Created by Z-H-Q on 2017/4/20.
 */
public class NewProduct extends Activity {
    EditText inputName;
    EditText inputPrice;
    EditText inputDesc;
    Button btnCreateProduct;
    String mUrl = "http://10.0.2.2/android_connect/create_product.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        inputName = (EditText) this.findViewById(R.id.inputName);
        inputPrice = (EditText) this.findViewById(R.id.inputPrice);
        inputDesc = (EditText) this.findViewById(R.id.inputDesc);
        btnCreateProduct = (Button) this.findViewById(R.id.btnCreateProduct);
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }
    public void commit(){
        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("name", String.valueOf(inputName.getText()));
        params.addQueryStringParameter("price", String.valueOf(inputPrice.getText()));
        params.addQueryStringParameter("description", String.valueOf(inputDesc.getText()));
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);

                finish();

            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });

    }
}
