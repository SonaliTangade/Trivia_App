package com.appscrip.trivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="My_Quiz.db";
    SQLiteDatabase db;

    public QuizDbHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        this.db=sqLiteDatabase;
        final String SQL_CREATE_QUERY="CREATE TABLE " +
                Quiz_Contract.QuestionsTable.TABLE_NAME + " ( "+
                Quiz_Contract.QuestionsTable._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Quiz_Contract.QuestionsTable.COLUMN + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " + ")";

        db.execSQL(SQL_CREATE_QUERY);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    db.execSQL(" DROP TABLE IF EXISTS "+ Quiz_Contract.QuestionsTable.TABLE_NAME);
    onCreate(db);
    }
    private void fillQuestionsTable()
    {
        Question q1=new Question("Question1","A","B","C",1);
        addQuestion(q1);
        Question q2=new Question("Question2","A","B","C",2);
        addQuestion(q2);
        Question q3=new Question("Question3","A","B","C",3);
        addQuestion(q3);
        Question q4=new Question("Question4","A","B","C",4);
        addQuestion(q4);

    }
    private void addQuestion(Question question)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Quiz_Contract.QuestionsTable.COLUMN,question.getQuestion());
        contentValues.put(Quiz_Contract.QuestionsTable.COLUMN_OPTION1,question.getOption1());
        contentValues.put(Quiz_Contract.QuestionsTable.COLUMN_OPTION2,question.getOption2());
        contentValues.put(Quiz_Contract.QuestionsTable.COLUMN_OPTION3,question.getOption3());
        contentValues.put(Quiz_Contract.QuestionsTable.COLUMN_ANSWER_NR,question.getAns());
        db.insert(Quiz_Contract.QuestionsTable.TABLE_NAME,null,contentValues);

    }
    public List<Question> getAllQuestions()
    {
        List<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + Quiz_Contract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst())
        {
            do {
                Question question=new Question();
                question.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN)));
                question.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_OPTION1)));
                question.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_OPTION2)));
                question.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_OPTION3)));
                question.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            }while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

}
