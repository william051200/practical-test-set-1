package my.tarc.set1ngweilun

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Grade
        val spinnerCourse: Spinner = findViewById(R.id.spinnerCourse)
        val txtGrade: TextView = findViewById(R.id.txtGrade)
        val btnCheck: Button = findViewById(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val error: Boolean = validation()
            var grade: String = ""
            var course: String = spinnerCourse.getSelectedItem().toString();

            if (!error) {
                grade = generateGrade()
                txtGrade.text = "Congratulations! Your grade for ${course} is ${grade}."
                reset()
            }
        }

        // Contact
        val btnContact: Button = findViewById(R.id.btnContact)

        btnContact.setOnClickListener{
            val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+044904311"))
            startActivity(call)
        }

//        val btnEmail: Button = findViewById(R.id.btnEmail)
//
//        btnEmail.setOnClickListener {
//
//            val email = Intent(Intent.ACTION_SEND)
//
//            email.setType("text/html");
//            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("ExamDepartment@staff.taruc.edu.my"))
//            email.putExtra(Intent.EXTRA_SUBJECT, "Subject")
//            email.putExtra(Intent.EXTRA_TEXT, "Message")
//
//            email.type = "message/rfc822"
//
//            startActivity(Intent.createChooser(email, "Send Email"));
//        }
    }

    private fun validation(): Boolean {
        val txtName: TextView = findViewById(R.id.txtName)
        val txtScore: TextView = findViewById(R.id.txtScore)
        var error: Boolean = false

        if (txtName.text.isEmpty()) {
            Toast.makeText(this, "Invalid Name", Toast.LENGTH_SHORT).show()
            error = true
        } else if (txtScore.text.isEmpty() || txtScore.text.toString()
                .toInt() > 100 || txtScore.text.toString().toInt() < 0
        ) {
            Toast.makeText(this, "Invalid Score", Toast.LENGTH_SHORT).show()
            error = true
        }

        return error;
    }

    private fun generateGrade(): String {
        val txtScore: TextView = findViewById(R.id.txtScore)
        val score: Double = txtScore.text.toString().toDouble()
        var grade: String = ""

        when (score) {
            in 80.0..100.0 -> grade = "A"
            in 75.0..79.0 -> grade = "A-"
            in 70.0..74.0 -> grade = "B+"
            in 65.0..69.0 -> grade = "B"
            in 60.0..64.0 -> grade = "B-"
            in 55.0..59.0 -> grade = "C+"
            in 50.0..54.0 -> grade = "C"
            in 0.0..49.0 -> grade = "D"
        }

        return grade
    }

    private fun reset() {
        val txtName: TextView = findViewById(R.id.txtName)
        val txtScore: TextView = findViewById(R.id.txtScore)
        val spinnerCourse: Spinner = findViewById(R.id.spinnerCourse)

        txtName.text = ""
        txtScore.text = ""
        spinnerCourse.setSelection(0)
    }
}