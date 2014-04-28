package com.asb.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import com.asb.todo.model.eo.Tags;
import com.asb.todo.model.eo.Todo;

import iiitb.dm.ormlibrary.ORMHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewTodo extends Activity {

  EditText name = null;
  EditText description = null;
  EditText tags = null;
  Button save = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_todo);
    final ORMHelper ormHelper = ORMHelper.getInstance(getApplicationContext());
    name = (EditText) findViewById(R.id.name);
    description = (EditText) findViewById(R.id.description);
    tags = (EditText) findViewById(R.id.tags);
    save = (Button) findViewById(R.id.save);
    save.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        StringTokenizer tagItems = new StringTokenizer(tags.getText()
            .toString());
        Collection<Tags> tagList = new ArrayList<Tags>(tagItems.countTokens());
        while (tagItems.hasMoreTokens()) {
          Tags tag = new Tags(tagItems.nextToken());
          tagList.add(tag);
        }
        Todo todo = new Todo(name.getText().toString(), description.getText()
            .toString(), tagList);
        ormHelper.persist(todo);
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
