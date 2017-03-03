package org.researchstack.backbone.ui.step.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import org.researchstack.backbone.ui.callbacks.StepCallbacks;
import org.researchstack.backbone.ui.step.body.BodyAnswer;


/**
 * Layout for FormStep which ensures proper behavior of the 'next' button; causes 'next'
 * to shift focus to the next text field or to submit form if no fields are left.
 *
 * Created by sdmartinez on 3/2/2017.
 */
public class FormStepLayout extends SurveyStepLayout {


    public FormStepLayout(Context context) {
        super(context);
    }

    public FormStepLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormStepLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onNextClicked() {

        BodyAnswer bodyAnswer = stepBody.getBodyAnswerState();
        View currentView = container.findFocus();
        View nextView = container.focusSearch(currentView, FOCUS_DOWN);

        if (nextView != null) {
            nextView.requestFocus();
        }
        else if (bodyAnswer == null || !bodyAnswer.isValid()) {
            Toast.makeText(
                    getContext(),
                    bodyAnswer == null
                            ? BodyAnswer.INVALID.getString(getContext())
                            : bodyAnswer.getString(getContext()),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            callbacks.onSaveStep(
                    StepCallbacks.ACTION_NEXT,
                    getStep(),
                    stepBody.getStepResult(false));
        }
    }

}
