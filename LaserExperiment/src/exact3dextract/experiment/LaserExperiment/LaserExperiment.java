package exact3dextract.experiment.LaserExperiment;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.widget.ImageView;

public class LaserExperiment extends Activity {
    Bitmap bitmap;
    ColorPoint colorPoint = new ColorPoint(-1,-1,0);
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeFile("laser_test.jpg", options);
        for(int i = 0; i < bitmap.getWidth(); i++){
            for(int j = 0; j < bitmap.getHeight(); j++){
                double tempBrightness = calculateBrightness(bitmap.getPixel(i, j));
                if(tempBrightness> colorPoint.getBrightness()){
                    colorPoint = new ColorPoint(i,j, tempBrightness);
                }
            }
        }

        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        Canvas canvas = new Canvas(mutableBitmap);
        canvas.drawCircle(colorPoint.getX(), colorPoint.getY(), 10, paint);
        imageView.setImageBitmap(bitmap);

    }

    public double calculateBrightness(int color){
        return 0.2126*Color.red(color) + 0.7152*Color.green(color) + 0.0722*Color.blue(color);
    }
}
