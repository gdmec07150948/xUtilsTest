package zhq.example.com.xutilstest.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import zhq.example.com.xutilstest.R;
import zhq.example.com.xutilstest.bean.ProductsListBean;

/**
 * Created by Z-H-Q on 2017/4/22.
 */
public class MyAdapter extends BaseAdapter {
    private BitmapUtils mBitmapUtils;
    private Context mContext;
    private List<ProductsListBean.ProductsBean> productsBeenList;
    public MyAdapter(Context context,List<ProductsListBean.ProductsBean> productsBeenList) {
        this.mContext = context;
        this.productsBeenList = productsBeenList;
        mBitmapUtils = new BitmapUtils(mContext);

    }
    @Override
    public int getCount() {
        return productsBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            holder.tvPid = (TextView) convertView.findViewById(R.id.tv_pid);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
            holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.tvCreatedAt = (TextView) convertView.findViewById(R.id.tv_created_at);
            holder.tvUpdatedAt = (TextView) convertView.findViewById(R.id.tv_updated_at);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPid.setText(String.valueOf(productsBeenList.get(position).getPid()));
        holder.tvName.setText(productsBeenList.get(position).getName());
        holder.tvPrice.setText(productsBeenList.get(position).getPrice());
        holder.tvDescription.setText(productsBeenList.get(position).getDescription());
        //holder.ivImage.setImageResource(R.drawable.book);
        mBitmapUtils.display(holder.ivImage,"http://10.0.2.2/android_connect/"+productsBeenList.get(position).getImage());
        holder.tvCreatedAt.setText(productsBeenList.get(position).getCreated_at());
        holder.tvUpdatedAt.setText(productsBeenList.get(position).getUpdated_at());
        return convertView;
    }
    private class ViewHolder {
        public TextView tvPid;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvDescription;
        public ImageView ivImage;
        public TextView tvCreatedAt;
        public TextView tvUpdatedAt;
    }
}
