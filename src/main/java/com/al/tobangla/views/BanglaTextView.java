package com.al.tobangla.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.al.tobangla.R;
import com.al.tobangla.processor.ToBangla;
import com.al.tobangla.utils.ProcessType;

import static com.al.tobangla.utils.ProcessType.ORDINAL_INDICATOR_FOR_DATE;
import static com.al.tobangla.utils.ProcessType.ORDINAL_INDICATOR_FOR_NUMERIC_ORDER;

/**
 * Created by User on 24/1/2018.
 */

public class BanglaTextView extends AppCompatTextView {

    private ProcessType processType;
    private ToBangla toBangla;
    private final String WHITE_SPACE = " ";
    private final String Tk = "৳" + WHITE_SPACE;

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public BanglaTextView(Context context) {
        super(context, null);
    }

    public BanglaTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        init(context, attrs);
    }

    public BanglaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        toBangla = ToBangla.getInstance();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BanglaTextView);

        int processTypeVal = typedArray.getInt(R.styleable.BanglaTextView_processType, 0);

        for (ProcessType pt : ProcessType.values()) {
            if (pt.getValue() == processTypeVal) {
                processType = pt;
                break;
            }
        }

        typedArray.recycle();

        processText(getText().toString());
    }

    private void processText(String text) {
        String value;

        switch (processType) {

            case DATE:
                value = toBangla.getDate(text);
                break;

            case NUMBER:
                value = toBangla.getNumber(text);
                break;

            case TIME:
                value = toBangla.getNumber(text);
                break;

            case AMOUNT:
                value = Tk + toBangla.getNumber(text);
                break;

            case ORDINAL_INDICATOR_FOR_DATE:
                value = toBangla.getOrdinalIndicator(text, ORDINAL_INDICATOR_FOR_DATE);
                break;

            case ORDINAL_INDICATOR_FOR_NUMERIC_ORDER:
                value = toBangla.getOrdinalIndicator(text, ORDINAL_INDICATOR_FOR_NUMERIC_ORDER);
                break;

            case ORDINAL_INDICATOR_TODAY:
                value = toBangla.getTodayDate();
                break;

            case TODAY:
                value = toBangla.getToday();
                break;

            case TIME_NOW:
                value = toBangla.getCurrentTime();
                break;

            case NOW_DATE_TIME:
                value = toBangla.getTodayDate() + WHITE_SPACE + toBangla.getCurrentTime();
                break;

            default:
                value = text;
                break;
        }
        setText(value);
    }



}
