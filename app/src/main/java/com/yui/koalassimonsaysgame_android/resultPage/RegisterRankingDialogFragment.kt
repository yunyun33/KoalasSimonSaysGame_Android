package com.yui.koalassimonsaysgame_android.resultPage

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.yui.koalassimonsaysgame_android.R

class RegisterRankingDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.activity_register_ranking_dialog_fragment, null)
        
        builder.setView(view)
            .setPositiveButton(this.getString(R.string.registerForRankingDialog_positiveText), DialogInterface.OnClickListener { dialog, which ->

                // OKボタン押したときの処理(rankingに登録する)
                val nameText = view.findViewById<EditText>(R.id.editText)
                nameText.setHint(this.getString(R.string.registerForRankingDialog_editText_hint))

                val userText = nameText.getText().toString().trim()

                // Fragment表示の確認のため、一旦処理はせずtoastにする。
                Toast.makeText(activity, nameText.text, Toast.LENGTH_SHORT).show()
//                presenter.didTapRegisterButton(userText)
            })

            .setNegativeButton(this.getString(R.string.registerForRankingDialog_negativeText), DialogInterface.OnClickListener { dialog, which ->

                // Fragment表示の確認のため、一旦処理はせずtoastにする。
                Toast.makeText(activity, "登録しない", Toast.LENGTH_SHORT).show()
//                presenter.didTapNoRegisterButton()
            })

        return builder.create()
    }

    companion object {
        fun newInstance(): RegisterRankingDialogFragment {
            return RegisterRankingDialogFragment()
        }
    }

}