package com.asb.todo;

import iiitb.dm.ormlibrary.ORMHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import com.asb.todo.model.eo.Tags;
import com.asb.todo.model.eo.Todo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditTodo extends Activity {

  EditText name = null;
  EditText description = null;
  EditText tags = null;
  Button update = null;
  Todo todo = null;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_todo);
    final ORMHelper ormHelper = ORMHelper.getInstance(getApplicationContext());
    name = (EditText) findViewById(R.id.name);
    description = (EditText) findViewById(R.id.description);
    tags = (EditText) findViewById(R.id.tags);
    update = (Button) findViewById(R.id.update);
    
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        long id = extras.getLong("todoId");
        todo = (Todo) ormHelper.find(Todo.class, id);
        name.setText(todo.getName());
        description.setText(todo.getDescription());
        StringBuilder sb = new StringBuilder();
        String space = "";
        for (Tags tag : todo.getTags()) {
          sb.append(space).append(tag.getName());
          space = " ";
        }
        tags.setText(sb.toString());
    }
    
    update.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        StringTokenizer tagItems = new StringTokenizer(tags.getText()
            .toString());
        
        for (Tags tag: todo.getTags()) {
          ormHelper.delete(tag);
        }
        
        Collection<Tags> tagList = new ArrayList<Tags>(tagItems.countTokens());
        while (tagItems.hasMoreTokens()) {
          Tags tag = new Tags(tagItems.nextToken());
          tagList.add(tag);
        }
        todo.setName(name.getText().toString());
        todo.setDescription(description.getText().toString());
        todo.setTags(tagList);
        
        ormHelper.update(todo);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.edit_todo, menu);
    return true;
  }
}
