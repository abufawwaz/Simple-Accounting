package com.emmanuelmess.simpleaccounting.activities.views;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.emmanuelmess.simpleaccounting.R;

import java.math.BigDecimal;

import static com.emmanuelmess.simpleaccounting.MainActivity.EDIT_IDS;
import static com.emmanuelmess.simpleaccounting.MainActivity.TEXT_IDS;

/**
 * @author Emmanuel
 *         on 7/8/2017, at 23:54.
 */

public class LedgerRow extends TableRow {

	protected LedgerView.BalanceFormatter formatter;

	private TextView dateText, referenceText, creditText, debitText, balanceText;
	private EditText dateEditText, referenceEditText, creditEditText, debitEditText;

	public LedgerRow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		dateText = ((TextView) findViewById(R.id.textDate));
		referenceText = ((TextView) findViewById(R.id.textRef));
		creditText = ((TextView) findViewById(R.id.textCredit));
		debitText = ((TextView) findViewById(R.id.textDebit));
		balanceText = (TextView) findViewById(R.id.textBalance);

		dateEditText = ((EditText) findViewById(R.id.editDate));
		referenceEditText = ((EditText) findViewById(R.id.editRef));
		creditEditText = ((EditText) findViewById(R.id.editCredit));
		debitEditText = ((EditText) findViewById(R.id.editDebit));
	}

	public void setDate(String date) {
		dateText.setText(date);
	}

	public void setReference(@StringRes int ref) {
		referenceText.setText(ref);
	}

	public void setReference(String ref) {
		referenceText.setText(ref);
	}

	public void setCredit(String credit) {
		creditText.setText(credit);
	}

	public void setDebit(String debit) {
		debitText.setText(debit);
	}


	public void setCredit(BigDecimal credit) {
		creditText.setText(credit.toPlainString());
	}

	public void setDebit(BigDecimal debit) {
		debitText.setText(debit.toPlainString());
	}

	public void setBalance(BigDecimal balance) {
		balanceText.setText(formatter.format(balance));
	}

	public void invertDebitCredit() {
		findViewById(R.id.textCredit).setId(0);
		findViewById(R.id.textDebit).setId(R.id.textCredit);
		findViewById(0).setId(R.id.textDebit);

		findViewById(R.id.editCredit).setId(0);
		findViewById(R.id.editDebit).setId(R.id.editCredit);
		findViewById(0).setId(R.id.editDebit);

		((EditText) findViewById(R.id.editCredit)).setHint(R.string.credit);
		((EditText) findViewById(R.id.editDebit)).setHint(R.string.debit);

		creditText = ((TextView) findViewById(R.id.textCredit));
		debitText = ((TextView) findViewById(R.id.textDebit));
		creditEditText = ((EditText) findViewById(R.id.editCredit));
		debitEditText = ((EditText) findViewById(R.id.editDebit));
	}

	public void makeRowNotEditable() {
		for (int i = 0; i < TEXT_IDS.length; i++) {
			EditText t = (EditText) findViewById(EDIT_IDS[i]);
			TextView t1 = (TextView) findViewById(TEXT_IDS[i]);

			t.setOnTouchListener(null);

			t1.setText(t.getText());
			t.setText("");

			t.setVisibility(GONE);
			t1.setVisibility(VISIBLE);
		}
	}

}
