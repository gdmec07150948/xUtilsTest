package zhq.example.com.xutilstest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import zhq.example.com.xutilstest.adapter.MyAdapter;
import zhq.example.com.xutilstest.bean.ProductsListBean;

public class MainActivity extends Activity {
    List<ProductsListBean.ProductsBean> productsBeanList;
    private MyAdapter myAdapter;
    Button btnCreate;
    Button btnShow;
    ListView lvList;
    String mUrl = "http://10.0.2.2/android_connect/get_all_products.php";
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsBeanList = new ArrayList<>();
        lvList = (ListView) this.findViewById(R.id.lv_list);
        myAdapter = new MyAdapter(this,productsBeanList);
        lvList.setAdapter(myAdapter);
        btnShow = (Button) this.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();

            }
        });
        btnCreate = (Button) this.findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),NewProduct.class);
                startActivity(intent);
            }
        });
        gson = new Gson();
    }

    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    public void initData(){
        //getDataFromServer();
        /*new Thread(new Runnable() {//网络请求必须在子线程中获取.现在模拟进行了一次网络请求,这里假设是网络请求完毕并且经过了回调
            @Override
            public void run() {

            }
        }).start();*/
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);

                //List<ProductsListBean> products = new ArrayList<ProductsListBean>();
                        /*Type type = new TypeToken<List<ProductsListBean>>(){}.getType();
                        ProductsListBean jsonBean = gson.fromJson(result,type);
                        System.out.println(jsonBean);
                        System.out.println(jsonBean.products.get(0).pid);
                        System.out.println(jsonBean.products.get(0).name);*/
                ProductsListBean productsListBean = gson.fromJson(result, new TypeToken<ProductsListBean>() {}.getType());

                        /*Type type = new TypeToken<ProductsListBean>() {}.getType();
                        List<ProductsListBean.ProductsBean> temp = gson.fromJson(result,new TypeToken<List<ProductsListBean.ProductsBean>>(){}.getType());
                        System.out.println(temp);*/
                        /*products = new ArrayList<ProductsBean.Products>();
                        System.out.println(products.get(0).pid);
                        System.out.println(products.get(0).name);*/

                //List<ProductsBean> list = gson.fromJson(result, new TypeToken<List<ProductsBean>>(){}.getType());
                       /* ProductsBean productsBean = gson.fromJson(result, ProductsBean.class);*/
                productsBeanList.clear();//先清空原有的list
                productsBeanList.addAll(productsListBean.getProducts());//再将获取到的数据完全add进productsBeanList.
                runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();//在主线程中刷新listView
                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }
    /*private void getDataFromServer(){
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                processData(result);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void processData(String result){
        Gson gson = new Gson();

        productsBean = gson.fromJson(result, ProductsBean.class);
        *//*int pid = productsBean.products.get(0).pid;
        String name = productsBean.products.get(0).name;
        String price = productsBean.products.get(0).price;
        String description = productsBean.products.get(0).description;
        String created_at = productsBean.products.get(0).created_at;
        String updated_at = productsBean.products.get(0).updated_at;
        System.out.println(pid +"  "+ name +"  "+price+"  "+description+"  "+created_at+"  "+updated_at);
        System.out.println(productsBean.products.size());*//*

        //myAdapter.notifyDataSetChanged();

    }*/

}
