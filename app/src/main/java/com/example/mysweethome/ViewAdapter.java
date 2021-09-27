package com.example.mysweethome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.sweetHouse;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.HomeViewHolder> {

    private ArrayList<sweetHouse> houses = new ArrayList<>();
    public static ArrayList imagesArray = new ArrayList();

    public ViewAdapter(ArrayList<sweetHouse> sweetHouseTaksts) {
        this.houses = sweetHouseTaksts;
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        public sweetHouse sweet;
        View itemView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.findViewById(R.id.seeMore).setOnClickListener(View -> {
                Intent intent = new Intent(View.getContext(), homeDetails.class);
                //Price
                intent.putExtra("Price", sweet.getPrice());
                //Location
                intent.putExtra("Address", sweet.getLocation());
                //Bundle of Images
                Bundle bundle = new Bundle();

                for (String s : sweet.getImage()) {
                    imagesArray.add(s);
                }
                bundle.putParcelableArrayList("Images", imagesArray);
                intent.putExtras(bundle);


                intent.putExtra("Area", sweet.getArea());
                intent.putExtra("Email", sweet.getEmail());
                intent.putExtra("Age", sweet.getAgeOfBuild());
                intent.putExtra("FloorNum", sweet.getFloors());
                intent.putExtra("Info", sweet.getMoreInfo());
                intent.putExtra("RoomNum", sweet.getNumberOfRooms());
                intent.putExtra("RentSell", sweet.getRentOfSell());
                intent.putExtra("Type", sweet.getType());

                // PoolBalcony
                String poolBalconyString="";
                if (sweet.getBalcony() == true && sweet.getPool() == true){
                    poolBalconyString="Has Balcony - Has Pool";
                }else if(sweet.getBalcony() == true && sweet.getPool() == false){
                    poolBalconyString="Has Balcony";
                }else if(sweet.getBalcony() == false && sweet.getPool() == true){
                    poolBalconyString="Has Pool";
                }else{
                    poolBalconyString="No Balcony nor Pool";
                }
                intent.putExtra("PoolBalcony", poolBalconyString);

                View.getContext().startActivity(intent);
            });

            //TODO: ADD CLICK LISTENER TO THE REMOVE BUTTON THAT IS VISIBLE ONLY IN THE PROFILE ACTIVITY
        }
    }

    @NonNull
    @Override
    public ViewAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.HomeViewHolder holder, int position) {
        holder.sweet = houses.get(position);

        TextView priceTextView = holder.itemView.findViewById(R.id.priceTextView);
        TextView addressTextView = holder.itemView.findViewById(R.id.addressTextView);
        ImageView itemImageView = holder.itemView.findViewById(R.id.itemImageView);

        priceTextView.setText(holder.sweet.getPrice());
        addressTextView.setText(holder.sweet.getLocation());
        itemImageView.setImageURI(Uri.parse(imagesArray.get(0).toString()));


    }

    @Override
    public int getItemCount() {
        return houses.size();
    }
}