package com.asb.todo;

import iiitb.dm.ormlibrary.ORMHelper;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.asb.todo.adapters.TodoArrayAdapter;
import com.asb.todo.model.eo.Todo;

public class Todos extends Activity {
  ListView listView = null;
  Button add = null;
  BaseAdapter todoAdapter = null;
  ORMHelper ormHelper = null;
  List<Todo> todos = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todos);
    ormHelper = ORMHelper.getInstance(getApplicationContext());
    todos = ormHelper.createCriteria(Todo.class).list();
    listView = (ListView) findViewById(R.id.todos);
    add = (Button) findViewById(R.id.addTodo);
    add.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent addTodo = new Intent(Todos.this, NewTodo.class);
        startActivityForResult(addTodo, 1);
      }
    });
    todoAdapter = new TodoArrayAdapter(getApplicationContext(), todos);

    listView.setAdapter(todoAdapter);

    listView.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position,
          long id) {
        Intent updateTodo = new Intent(getApplicationContext(), EditTodo.class);
        updateTodo.putExtra("todoId", id);
        startActivityForResult(updateTodo, 1);
      }
    });

    listView
        .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          public boolean onItemLongClick(AdapterView<?> parent, View v,
              int position, long id) {
            Todo todo = (Todo) todoAdapter.getItem(position);
            ormHelper.delete(todo);
            todos.clear();
            List<Todo> ts = ormHelper.createCriteria(Todo.class).list();
            todos.addAll(ts);
            todoAdapter.notifyDataSetChanged();
            return true;
          }
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.todos, menu);
    return true;
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == 1) {

      if (resultCode == RESULT_OK) {
        todos.clear();
        List<Todo> ts = ormHelper.createCriteria(Todo.class).list();
        todos.addAll(ts);
        todoAdapter.notifyDataSetChanged();
      }
      if (resultCode == RESULT_CANCELED) {
      }
    }
  }
}
