package com.asb.todo.adapters;

import java.util.List;

import com.asb.todo.R;
import com.asb.todo.model.eo.Tags;
import com.asb.todo.model.eo.Todo;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoArrayAdapter extends BaseAdapter {
  private final Context context;
  private final List<Todo> todos;

  public TodoArrayAdapter(Context context, List<Todo> todos) {  
    super();
    this.context = context;
    this.todos = todos;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View rowView = null;

    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    rowView = inflater.inflate(R.layout.todo_item, parent, false);

    TextView name = (TextView) rowView.findViewById(R.id.name);
    TextView description = (TextView) rowView.findViewById(R.id.description);
    TextView tags = (TextView) rowView.findViewById(R.id.tags);
    
    name.setTextColor(Color.BLACK);
    description.setTextColor(Color.BLACK);
    tags.setTextColor(Color.BLACK);

    Todo todo = todos.get(position);
    name.setText(todo.getName());
    description.setText(todo.getDescription());
    StringBuilder sb = new StringBuilder();
    String comma = "";
    for (Tags tag : todo.getTags()) {
      sb.append(comma).append(tag.getName());
      comma = ", ";
    }
    tags.setText(sb.toString());
    return rowView;
  }

  @Override
  public int getCount() {
    return todos.size();
  }

  @Override
  public Object getItem(int pos) {
    return todos.get(pos);
  }

  @Override
  public long getItemId(int pos) {
    return todos.get(pos).getId();
  }
}
