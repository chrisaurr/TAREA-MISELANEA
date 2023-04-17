 package com.example.gestionbiblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    //EditText libroPrestamo, usuarioPrestamo, libroDevolver;
    Button prestamo, devolver;

    Spinner sp1, sp2, sp3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*libroPrestamo = findViewById(R.id.codLibro);
        usuarioPrestamo = findViewById(R.id.codUsuario);
        libroDevolver = findViewById(R.id.idLibro);*/

        prestamo = findViewById(R.id.btnPrestamo);
        devolver = findViewById(R.id.btnDevolucion);

        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);

        List<Usuario> listaUsuarios = llenarSpinnerU();
        ArrayAdapter<Usuario> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaUsuarios);
        sp1.setAdapter(arrayAdapter);

        List<Libro> listaLibros = llenarSpinnerL();
        ArrayAdapter<Libro> arrayAdapter1 = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaLibros);
        sp2.setAdapter(arrayAdapter1);

        List<Libro> listaLibrosPorDevolver = llenarSpinnerLD();
        ArrayAdapter<Libro> arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaLibrosPorDevolver);
        sp3.setAdapter(arrayAdapter2);

        prestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esDisponible();
                consultar();
            }
        });

        devolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolverlo();
                consultar();
            }
        });
    }

    public void esDisponible(){
        try {
            AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            //String codigo = libroPrestamo.getText().toString();
            String codigo = (((Libro) sp2.getSelectedItem()).id);
            Cursor cursor = db.rawQuery("SELECT disponible from libro where id="+codigo+"", null);
            if(cursor.moveToFirst()){
                if(cursor.getString(0).toString().equals("1")){
                    prestar();
                }else{
                    Toast.makeText(this, "Libro no disponible", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "No exiten coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception ex){
            Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void prestar(){
        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
      //  String codigo = libroPrestamo.getText().toString();
        //String usuario = usuarioPrestamo.getText().toString();
        Boolean disponible = false;
        String codigo = (((Libro) sp2.getSelectedItem()).id);
        String usuario = (((Usuario) sp1.getSelectedItem()).id);

        Log.v("Tag", codigo);

        ContentValues contenedor = new ContentValues();

        contenedor.put("id_usuario", usuario);
        contenedor.put("disponible", disponible);

        int dato = db.update("libro", contenedor, "id="+codigo+"", null);

        if(dato == 1){
            Toast.makeText(this, "Dato modificados con exito", Toast.LENGTH_SHORT).show();
            List<Libro> listaLibrosPorDevolver = llenarSpinnerLD();
            ArrayAdapter<Libro> arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaLibrosPorDevolver);
            sp3.setAdapter(arrayAdapter2);
        }else{
            Toast.makeText(this, "No se encuentra el articulo", Toast.LENGTH_SHORT).show();
        }
    }

     public void devolverlo(){
         AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
         SQLiteDatabase db = admin.getWritableDatabase();
         //String codigo = libroPrestamo.getText().toString();
         Boolean disponible = true;
         String codigo = ((Libro) sp2.getSelectedItem()).id;

         ContentValues contenedor = new ContentValues();

         contenedor.put("disponible", disponible);

         int dato = db.update("libro", contenedor, "id="+codigo+"", null);

         if(dato == 1){
             Toast.makeText(this, "Dato modificados con exito", Toast.LENGTH_SHORT).show();
             List<Libro> listaLibrosPorDevolver = llenarSpinnerLD();
             ArrayAdapter<Libro> arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaLibrosPorDevolver);
             sp3.setAdapter(arrayAdapter2);

         }else{
             Toast.makeText(this, "No se encuentra el articulo", Toast.LENGTH_SHORT).show();
         }
     }

     public void limpiarDatos(){
       /* libroPrestamo.setText("");
        usuarioPrestamo.setText("");
        libroDevolver.setText("");*/
     }

     public void consultar(){
         try {
             AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
             SQLiteDatabase db = admin.getWritableDatabase();
             //String codigo = libroPrestamo.getText().toString();
             String buscar = "0";
             String codigo = ((Libro) sp2.getSelectedItem()).id;
            // Cursor cursor = db.rawQuery("SELECT nom_libro, autor, usuario.nombres, usuario.apellidos, disponible from libro INNER JOIN usuario ON libro.id = "+codigo+"", null);
             Cursor cursor = db.rawQuery("SELECT nom_libro, autor, id_usuario, disponible from libro where id = "+codigo+"", null);
             if(cursor.moveToFirst()){
                 /*txtcodigo.setText(cursor.getString(0).toString());
                 txtdescripcion.setText(cursor.getString(1).toString());
                 txtprecio.setText(cursor.getString(2).toString());
                 txtcantidad.setText(cursor.getString(3).toString());*/
                 /*Log.v("Pruebas", cursor.getString(0).toString() + " " + cursor.getString(1).toString() + " " +
                         cursor.getString(2).toString() + " " + cursor.getString(3).toString() + " " +
                         cursor.getString(4).toString());*/
                 Log.v("Pruebas", cursor.getString(0).toString() + " " + cursor.getString(1).toString() + " " +
                            cursor.getString(3).toString());
                 buscar = cursor.getString(2).toString();


             }else{
                 Toast.makeText(this, "No exiten coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
             }

             Cursor cursor1 = db.rawQuery("SELECT nombres, apellidos from usuario where id = "+buscar+"", null);
             if(cursor1.moveToFirst()){
                 /*txtcodigo.setText(cursor.getString(0).toString());
                 txtdescripcion.setText(cursor.getString(1).toString());
                 txtprecio.setText(cursor.getString(2).toString());
                 txtcantidad.setText(cursor.getString(3).toString());*/
                 /*Log.v("Pruebas", cursor.getString(0).toString() + " " + cursor.getString(1).toString() + " " +
                         cursor.getString(2).toString() + " " + cursor.getString(3).toString() + " " +
                         cursor.getString(4).toString());*/
                 Log.v("Pruebas", cursor1.getString(0).toString() + " " + cursor1.getString(1).toString());
             }

         }catch (Exception ex){
             Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show();
         }
     }

     @SuppressLint("Range")
     public List<Usuario> llenarSpinnerU(){
         List<Usuario> listUser = new ArrayList<>();
         try {
             AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "usuario", null, 1);
             SQLiteDatabase db = admin.getWritableDatabase();

             Cursor cursor = db.rawQuery("SELECT id, nombres, apellidos from usuario", null);
             if(cursor != null){
                 if (cursor.moveToFirst()) {
                    do{
                        Usuario user = new Usuario();
                        user.setId(cursor.getString(cursor.getColumnIndex("id")));
                        user.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
                        user.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
                        listUser.add(user);
                    }while (cursor.moveToNext());

                 } else {
                     Toast.makeText(this, "No exiten coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
                 }
             }
             db.close();

         }catch (Exception ex){
             Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show();
         }
         return listUser;
     }

     @SuppressLint("Range")
     public List<Libro> llenarSpinnerL(){
         List<Libro> listBook = new ArrayList<>();
         try {
             AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
             SQLiteDatabase db = admin.getWritableDatabase();

             Cursor cursor = db.rawQuery("SELECT id, nom_libro, autor from libro", null);
             if(cursor != null){
                 if (cursor.moveToFirst()) {
                     do{
                         Libro book = new Libro();
                         book.setId(cursor.getString(cursor.getColumnIndex("id")));
                         book.setNom_libro(cursor.getString(cursor.getColumnIndex("nom_libro")));
                         book.setAutor(cursor.getString(cursor.getColumnIndex("autor")));
                         listBook.add(book);
                     }while (cursor.moveToNext());

                 } else {
                     Toast.makeText(this, "No exiten coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
                 }
             }
             db.close();

         }catch (Exception ex){
             Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show();
         }
         return listBook;
     }

     @SuppressLint("Range")
     public List<Libro> llenarSpinnerLD(){
         List<Libro> listBook = new ArrayList<>();
         try {
             AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "libro", null, 1);
             SQLiteDatabase db = admin.getWritableDatabase();
             int f = 0;

             Cursor cursor = db.rawQuery("SELECT id, nom_libro, autor from libro where disponible = "+f+"", null);
             if(cursor != null){
                 if (cursor.moveToFirst()) {
                     do{
                         Libro book = new Libro();
                         book.setId(cursor.getString(cursor.getColumnIndex("id")));
                         book.setNom_libro(cursor.getString(cursor.getColumnIndex("nom_libro")));
                         book.setAutor(cursor.getString(cursor.getColumnIndex("autor")));
                         listBook.add(book);
                     }while (cursor.moveToNext());

                 } else {
                     Toast.makeText(this, "No exiten coincidencias en la base de datos", Toast.LENGTH_SHORT).show();
                 }
             }
             db.close();

         }catch (Exception ex){
             Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show();
         }
         return listBook;
     }
}