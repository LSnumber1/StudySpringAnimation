package connectgames.com.studyspringanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GamePauseMenu gamePauseMenu;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamePauseMenu = findViewById(R.id.gamePauseMenu);
        button = findViewById(R.id.button);
        gamePauseMenu.setMenuListener(new GamePauseMenu.IGamePauseMenuListener() {

            @Override
            public void onExit() {
                gamePauseMenu.hideAnimal();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePauseMenu.showAnimal();
            }
        });
    }
}
