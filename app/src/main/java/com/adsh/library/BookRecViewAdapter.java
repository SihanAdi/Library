package com.adsh.library;

import static com.adsh.library.BookActivity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder.txtName.setText(books.get(holder.getAdapterPosition()).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(holder.getAdapterPosition()).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(holder.getAdapterPosition()).getAuthor());
        holder.txtDescription.setText(books.get(holder.getAdapterPosition()).getShortDesc());

        if (books.get(holder.getAdapterPosition()).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedPelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }else {
                switch (parentActivity){
                    case "alreadyRead":
                        holder.btnDelete.setVisibility(View.VISIBLE);
                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to remove "
                                        + books.get(holder.getAdapterPosition()).getName() + " ?");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getUtils(context).removeFromAlreadyRead(books.get(holder.getAdapterPosition()))){
                                            Toast.makeText(context,  "Book Removed", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }else {
                                            Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.create().show();

                            }
                        });
                        break;
                    case "wantToRead":
                        holder.btnDelete.setVisibility(View.VISIBLE);
                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to remove "
                                        + books.get(holder.getAdapterPosition()).getName() + " ?");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getUtils(context).removeFromWantToRead(books.get(holder.getAdapterPosition()))){
                                            Toast.makeText(context,  "Book Removed", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }else {
                                            Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.create().show();

                            }
                        });
                        break;
                    case "currentlyReading":
                        holder.btnDelete.setVisibility(View.VISIBLE);
                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to remove "
                                        + books.get(holder.getAdapterPosition()).getName() + " ?");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getUtils(context).removeFromCurrentlyReading(books.get(holder.getAdapterPosition()))){
                                            Toast.makeText(context,  "Book Removed", Toast.LENGTH_SHORT).show();
                                            //TODO:Callback function to resolve not update page problem
                                            notifyDataSetChanged();
                                        }else {
                                            Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.create().show();

                            }
                        });
                        break;
                    case "FavoriteBooks":
                        holder.btnDelete.setVisibility(View.VISIBLE);
                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to remove "
                                        + books.get(holder.getAdapterPosition()).getName() + " ?");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getUtils(context).removeFromFavoriteBooks(books.get(holder.getAdapterPosition()))){
                                            Toast.makeText(context,  "Book Removed", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }else {
                                            Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.create().show();

                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedPelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView imgBook;
        private TextView txtName;

        private ImageView downArrow, upArrow;
        private TextView txtAuthor, txtDescription, btnDelete;
        private RelativeLayout expandedPelLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            expandedPelLayout = itemView.findViewById(R.id.expandedRelLayout);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
