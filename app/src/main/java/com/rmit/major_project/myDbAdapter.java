package com.rmit.major_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String Table_name, ContentValues contentValues)//Generic insert function
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

   /* public String Does_User_have_access(){
        SQLiteDatabase db=myhelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("select name from users where name=test",null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }*/

    public boolean getaccess(String Staff_name, String Room_number) //Test data getting function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Staff_name,Room_number};
        Cursor cursor=db.rawQuery(" select s.name, c.access_group,c.rm_number from staff s, has_access h, can_be_accessed c where s.ID=h.ID and h.access_group=c.access_group and s.name=? and c.rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex("name"));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append( "name: "+name +'\n');
        }
        if (buffer.length()<1) {
            return false;
        }
        return true;
    }
    public boolean roomexists(String Room_number) //Test data getting function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Room_number};
        Cursor cursor=db.rawQuery(" select rm_number from room where rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex("rm_number"));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append(name);
        }
        if (buffer.length()<1) {
            return false;
        }
        return true;
    }
    public String getvaluefromroom( String Room_number,String value) //generic data getting function from room table
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Room_number};
        Cursor cursor=db.rawQuery("select * from room where rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex(value));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append( name);
        }
        return buffer.toString();

    }

    public String gettemp( String Room_number) //Test data getting function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Room_number};
        Cursor cursor=db.rawQuery("select * from room where rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex("temp"));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append( name);
        }
            return buffer.toString();

    }
    public String gethum( String Room_number) //Test data getting function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Room_number};
        Cursor cursor=db.rawQuery("select * from room where rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex("hum"));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append( name);
        }
        return buffer.toString();

    }
    public String getlux( String Room_number) //Test data getting function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.Access_lvl};
        //Cursor cursor =db.rawQuery("select * from staff ",null);
        String[] args={Room_number};
        Cursor cursor=db.rawQuery("select * from room where rm_number=?",args);
        //Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            //int cid =cursor.getInt(cursor.getColumnIndex("access_group"));
            String name =cursor.getString(cursor.getColumnIndex("lux"));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.Access_lvl));
            buffer.append( name);
        }
        return buffer.toString();

    }

    public  int delete(String Table_name, String tuple, String data) //Generic delete row function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={data};
        int count =db.delete(Table_name ,tuple+" = ?",whereArgs);
        return  count;
    }

    public int roomupdate(String attribute, String newData,String row)//Generic update function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(attribute,newData);
        String[] whereArgs= {row};
        int count =db.update("room",contentValues, "rm_number= ?",whereArgs);
        return count;
    }
    public int accessupdate(String newData,String row)//Generic update function
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("access_group",newData);
        String[] whereArgs= {row};
        int count =db.update("can_be_accessed",contentValues, "rm_number= ?",whereArgs);
        return count;
    }




    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "Users";   // Table Name
        private static final int DATABASE_Version = 4;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String Access_lvl= "Access_lvl";    // Column III
        private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ Access_lvl+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP table IF EXISTS "+"room";
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        public void issue_sql_command(SQLiteDatabase db, String command)
        {
            try {
                Log.i("Database","issuing an SQL command");
                db.execSQL(command);
            } catch (Exception e) {
                Log.i("Database",e.toString());
                Message.message(context,""+e);
            }

        }
        public void initialise_major_proj_data(SQLiteDatabase db)
        {
            issue_sql_command(db,"create table staff (ID int not null unique,name varchar(50) not null);");
            issue_sql_command(db,"insert into staff values (1,'staff1'),(2,'staff2'),(3,'staff3'),(4,'staff4');");

            issue_sql_command(db,"create table has_access (ID int not null, access_group int not null);");
            issue_sql_command(db,"insert into has_access values (1,0),(2,1),(2,2),(3,2),(4,3);");

            issue_sql_command(db,"create table can_be_accessed (rm_number int not null,access_group int not null);");
            issue_sql_command(db,"insert into can_be_accessed values (1,0),(2,1),(3,2),(4,3);");

            issue_sql_command(db,"create table room (rm_number int not null,hum int not null,lux int not null, temp int not null, ltemp int not null,htemp int not null,lhum int not null,hhum int not null,llux int not null,hlux int not null,origin varchar(255) not null,description varchar(255) not null);");
            issue_sql_command(db,"insert into room values (1,53,72,47,12,43,4,92,51,67,'Smithsonian','desc1'),(2,53,72,47,18,84,2,79,21,93,'Museum of Victoria','desc2'),(3,53,72,47,13,68,24,77,31,92,'Museum of South Australia','desc3'),(4,53,72,47,21,64,41,52,1,99,'Museum of Tasmania','desc4');");

        }

        public void onCreate(SQLiteDatabase db) {
            Log.i("DATABASE","cREATING THE DATABASE");
            issue_sql_command(db,CREATE_TABLE1);
            initialise_major_proj_data(db);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL("drop table if exists staff");
                db.execSQL("drop table if exists has_access");
                db.execSQL("drop table if exists can_be_accessed");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}