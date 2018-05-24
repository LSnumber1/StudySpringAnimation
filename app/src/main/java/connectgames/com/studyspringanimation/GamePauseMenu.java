package connectgames.com.studyspringanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * <pre>
 * author :ls
 * time :2018/05/21
 * desc:
 * version :
 * </pre>
 * @author ls
 */
public class GamePauseMenu extends RelativeLayout {

    private IGamePauseMenuListener menuListener;
    private ConstraintLayout constraintLayout;
    private Button closeBtn;
    private float height;

    /**
     * 定义监听接口
     */
    public interface IGamePauseMenuListener {

        void onExit();
    }

    public GamePauseMenu(Context context) {
        super(context);
        init(context);
    }

    public GamePauseMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GamePauseMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.view_pause_layout, this);
        constraintLayout = child.findViewById(R.id.content);
        closeBtn = child.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuListener != null) {
                    menuListener.onExit();
                }
            }
        });

    }

    public void setMenuListener(IGamePauseMenuListener menuListener) {
        this.menuListener = menuListener;
    }

    /**
     * 开始动画
     */
    public void showAnimal() {
        setVisibility(View.VISIBLE);
        SpringAnimation signUpBtnAnimY = new SpringAnimation(constraintLayout, DynamicAnimation.TRANSLATION_Y, 0);
        signUpBtnAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        signUpBtnAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        signUpBtnAnimY.setStartVelocity(5000);
        signUpBtnAnimY.start();
    }
    /**
     * 结束动画
     */
    public void hideAnimal() {
        height = (ScreenTools.getScreenHeight(getContext()) - constraintLayout.getHeight()) / 2 + constraintLayout.getHeight() + ScreenTools.dp2px(getContext(),50);
        ObjectAnimator animator = ObjectAnimator.ofFloat(constraintLayout, "translationY", 0f, -100f, height);
        animator.setDuration(600);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setVisibility(GONE);
                reLayout();
            }
        });
        animator.start();
    }

    private void reLayout(){
        constraintLayout.setTranslationY(-height + 100);
    }
}
