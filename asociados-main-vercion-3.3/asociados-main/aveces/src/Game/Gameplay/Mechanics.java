package Game.Gameplay;
import Game.Player;
import imput.KeyBoard;

public class Mechanics {
    public static void updateMechanics(Player player){
        if(KeyBoard.c){
            player.setCongelado(true);
            double dirX = 0;
            double dirY = 0;

            if (KeyBoard.up) dirY = -1;
            /*if (KeyBoard.down) dirY = 1;  // si quieres disparar hacia abajo*/
            if (KeyBoard.left) dirX = -1;
            if (KeyBoard.right) dirX = 1;
            
            if (dirX != 0 && dirY != 0) {
                double len = Math.sqrt(dirX*dirX + dirY*dirY);
                dirX /= len;
                dirY /= len;
            }
                if(KeyBoard.up && (KeyBoard.left==false || KeyBoard.right==false)){
                    player.setBulletDirY(-45);
                    player.setBulletDirX(0);
                }else if(KeyBoard.up && (KeyBoard.left||KeyBoard.right)){
                    player.setBulletDirY(0);
                    player.setBulletDirX(0);
                }
                if(KeyBoard.up==false && (KeyBoard.left || KeyBoard.right)){
                    player.setBulletDirY(0);
                    player.setBulletDirX(55);
                }
        }else{
            player.setCongelado(false);
            player.setBulletDirY(1);
            player.setBulletDirX(55);
        }
    }
}
