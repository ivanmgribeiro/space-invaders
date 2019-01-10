package main.collision;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import main.entity.Entity;

public class BoundingBox {
    private double x, y;
    private boolean[][] space;


    private BoundingBox() {

    }

    private BoundingBox(boolean[][] space, double x, double y) {
        this.space = space;
        this.x = x;
        this.y = y;
    }

    public static BoundingBox generateBoundingBox(Entity ent) {
        Image img = ent.getImage();
        boolean[][] space = new boolean[(int)img.getWidth()][(int)img.getHeight()];
        PixelReader pixelReader = img.getPixelReader();
        //System.out.println(ent.getClass());
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                // if pixel is not transparent, assume there is collision there
                //System.out.printf("%03d ", pixelReader.getArgb(i, j)>>>24);
                space[i][j] = pixelReader.getArgb(i, j)>>>24 > 1;
            }
            //System.out.println();
        }

        return new BoundingBox(space, ent.getX(), ent.getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /*
        returns 0 if no collision
        otherwise if there is a collision, returns number of overlapping pixels
     */
    public int collidesWith(BoundingBox other) {
        int localXAligned = (int) x, localYAligned = (int) y;
        int remoteXAligned = (int) other.x, remoteYAligned = (int) other.y;

        if (this.space.length == 0 || other.space.length == 0) {
            return 0;
        }

        if (remoteXAligned > localXAligned + this.space.length) {
            // no overlap, other box is too far to the right
            return 0;
        } else if (localXAligned > remoteXAligned + other.space.length) {
            // no overlap, this box is too far to the right
            return 0;
        } else if (remoteYAligned > localYAligned + this.space[0].length) {
            // no overlap, other box is too far down
            return 0;
        } else if (localYAligned > remoteYAligned + other.space[0].length) {
            // no overlap, this box is too far down
            return 0;
        }


        int overlapWidth, overlapHeight;
        int localOffsetX, localOffsetY;
        int remoteOffsetX, remoteOffsetY;

        if (remoteXAligned < localXAligned) {
            localOffsetX = 0;
            remoteOffsetX = localXAligned - remoteXAligned;
            if (remoteXAligned + other.space.length > localXAligned + this.space.length) {
                overlapWidth = this.space.length;
            } else {
                overlapWidth = remoteXAligned + other.space.length - localXAligned;
            }
        } else {
            localOffsetX = remoteXAligned - localXAligned;
            remoteOffsetX = 0;
            if (localXAligned + this.space.length > remoteXAligned + this.space.length) {
                overlapWidth = other.space.length;
            } else {
                overlapWidth = localXAligned + this.space.length - remoteXAligned;
            }
        }

        if (remoteYAligned < localYAligned) {
            localOffsetY = 0;
            remoteOffsetY = localYAligned - remoteYAligned;
            if (remoteYAligned + other.space[0].length > localYAligned + this.space[0].length) {
                overlapHeight = this.space[0].length;
            } else {
                overlapHeight = remoteYAligned + other.space[0].length - localYAligned;
            }
        } else {
            localOffsetY = remoteYAligned - localYAligned;
            remoteOffsetY = 0;
            if (localYAligned + this.space[0].length > remoteYAligned + this.space[0].length) {
                overlapHeight = other.space[0].length;
            } else {
                overlapHeight = localYAligned + this.space[0].length - remoteYAligned;
            }
        }

        int totalOverlap = 0;
        for (int i = 0; i < overlapWidth; i++) {
            for (int j = 0; j < overlapHeight; j++) {
                if (this.space[localOffsetX + i][localOffsetY + j]
                        & other.space[remoteOffsetX + i][remoteOffsetY + j]) {
                    totalOverlap++;
                }
            }
        }

        return totalOverlap;
    }
}
