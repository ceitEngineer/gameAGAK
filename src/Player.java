import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by ABOLFZL on 7/3/2017.
 */
public class Player extends Motile implements Movable {
    Vector<Partner> partners = new Vector<>(10);
    Vector<Texture> texturesInMoving = new Vector<>();
    boolean isNewVelcity = true;
    boolean isFinished = false;
    private Texture origTex;

    Player(double x, double y, int i, int j) {
        super(x, y, i, j);
        addPartners();

    }

    private void addPartners() {
        for (int k = 0; k < 10; k++) {
            partners.add(new Partner());
        }
    }

    private int selectDirection(Texture originTexture, Texture nextDest) {
        int direction = 0;
        int iOr = originTexture.getI();
        int jOr = originTexture.getJ();
        int iDest = nextDest.getI();
        int jDest = nextDest.getJ();

        if (iDest == iOr - 1 && jDest == jOr)
            direction = 1;
        else if (iDest == iOr - 1 && jDest == jOr - 1)
            direction = 2;
        else if (iDest == iOr && jDest == jOr - 1)
            direction = 3;
        else if (iDest == iOr + 1 && jDest == jOr - 1)
            direction = 4;
        else if (iDest == iOr + 1 && jDest == jOr)
            direction = 5;
        else if (iDest == iOr + 1 && jDest == jOr + 1)
            direction = 6;
        else if (iDest == iOr && jDest == jOr + 1)
            direction = 7;
        else if (iDest == iOr - 1 && jDest == jOr + 1)
            direction = 8;

        return direction;
    }

    private int selectDirection(int iOr, int jOr, Texture nextDest) {
        int direction;
        int iDest = nextDest.getI();
        int jDest = nextDest.getJ();
        System.out.println("ior jor " + iOr + "   " + jOr + "iDest jDest:: " + iDest + "   " + jDest);
        //  System.exit(0);
        if (iDest == iOr - 1 && jDest == jOr)
            direction = 1;
        else if (iDest == iOr - 1 && jDest == jOr - 1)
            direction = 2;
        else if (iDest == iOr && jDest == jOr - 1)
            direction = 3;
        else if (iDest == iOr + 1 && jDest == jOr - 1)
            direction = 4;
        else if (iDest == iOr + 1 && jDest == jOr)
            direction = 5;
        else if (iDest == iOr + 1 && jDest == jOr + 1)
            direction = 6;
        else if (iDest == iOr && jDest == jOr + 1)
            direction = 7;
        else if (iDest == iOr - 1 && jDest == jOr + 1)
            direction = 8;
        else direction = -1;

        return direction;
    }

    private Image getCorrectDirImage(int direction, String formatImg) {
        String pathPhoto = "images\\soldier\\";
        switch (direction) {
            case 1:
                pathPhoto += "1." + formatImg;
                break;
            case 2:
                pathPhoto += "2." + formatImg;
                break;
            case 3:
                pathPhoto += "3." + formatImg;
                break;
            case 4:
                pathPhoto += "4." + formatImg;
                break;
            case 5:
                pathPhoto += "5." + formatImg;
                break;
            case 6:
                pathPhoto += "6." + formatImg;
                break;
            case 7:
                pathPhoto += "7." + formatImg;
                break;
            case 8:
                pathPhoto += "8." + formatImg;
                break;
            default:
                System.out.println("Not direction existed");
                return null;
        }

        return new ImageIcon(pathPhoto).getImage();
    }
//////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    /**
     * new Algorithm for routing and directing to destinection
     */
    Texture[][] texturesPosib;
    Stack<Stack<Texture>> ssTex = new Stack<Stack<Texture>>();
    Stack<Texture> stackTex = new Stack<>();
    Stack<Stack<Integer>> sstackDir = new Stack<Stack<Integer>>();
    Stack<Integer> stackDir = new Stack<Integer>();
    int iposib = 0;
    int jposib = 0;
    boolean isFirstTime = true;
    boolean needNewStack = false;
    Texture nextTex;
    private int[] bestOfferedDirsOfDirOf(int dir,Texture destTex){
return new int[3];
    }



    synchronized Stack<Stack<Texture>> newAlgorithm(PlayingPanel pp, int dir, Texture originTex) {
        // System.out.println("in Algorithm" +( kkk++));
        int dirhere;
        int[] dirs = dirsOfDirOf(dir);
        if (isFirstTime) {
           // System.out.println("is firstorigin before tex is:" + originTex.getI() + "     " + originTex.getJ());
            stackTex.push(originTex);
            stackDir.push(dir);
            nextTex = nextOfThisDirThisTex(pp, dir, originTex);
            //System.out.println("is next first  tex is:" + nextTex.getI() + "     " + nextTex.getJ());

            isFirstTime = false;
            if (isWaySafe(nextTex,stackTex)) {
                originTex = nextTex;
                stackTex.push(originTex);
                stackDir.push(dir);
                System.out.println("1nex " + originTex.getI() + "  " + originTex.getJ());
               // ssTex.push(stackTex);
                //sstackDir.push(stackDir);
               System.out.println(stackTex.size() + "size stack first ");

                if (isDestinationTex(nextTex, pp.destTexture)) {
                   // System.out.println("is dest");
                    return ssTex;
                }
            } else {
                return null;
            }
        }
        for (int l = 0; l < dirs.length; l++) {
            if (needNewStack) {
                System.out.println("push stack to stack"+needNewStack);
                ssTex.push(stackTex);
                sstackDir.push(stackDir);
               System.out.println(" s s s    "+sstackDir.size());
            }

            // System.out.println("in L::" + l);
            dirhere = dirs[l];
            System.out.println("origin " + originTex.getI() + "  " + originTex.getJ());
            nextTex = nextOfThisDirThisTex(pp, dirhere, originTex);
          if (nextTex!=null)System.out.println("next " + nextTex.getI() + "  " + nextTex.getJ());
          //  System.out.println("direhere:: " + dirhere);
            System.out.println("size stack out if "+ stackTex.size() );

            if (isWaySafe(nextTex,stackTex) && !isrepeadedInStack(nextTex, stackTex)) {
                originTex = nextTex;
                System.out.println("origin after nex " + originTex.getI() + "  " + originTex.getJ());
                stackTex.push(originTex);
                stackDir.push(dirhere);
                System.out.println("size stack  "+ stackTex.size() );
                //  texturesPosib[iposib++][jposib] = originTex;
                if (isDestinationTex(originTex, pp.destTexture)) {
                    needNewStack = false;
                    System.out.println("in correct dest ...........");
                    System.exit(0);
                    continue;
                    // ssTex.push(stackTex);
                } else {
                   // needNewStack = false;
                    System.out.println(" s s s    "+sstackDir.size());
                    newAlgorithm(pp, dirhere, originTex);
                   // continue;
                }
            } else {
               // System.out.println(kkk+"in else of safe neednewstack bef"+needNewStack+l);
                //  ssTex.pop();
              needNewStack = false;
              //  System.out.println(kkk+"in else of safe neednewstack aft "+needNewStack+l);
                continue;
            }
            stackTex=copyStackToNewStack(stackTex);
            needNewStack = true;
          //  System.out.println("l  "+l+needNewStack+"tehe for "+kkk);
            }
        return ssTex;
    }
private synchronized Stack<Texture> copyStackToNewStack(Stack<Texture> beforStack){
    System.out.println("beforStack.size()"+beforStack.size());
    Stack<Texture> newStack=new Stack<>();
    for (int i = 0; i < beforStack.size(); i++) {
        newStack.push(beforStack.elementAt(i));
    }
    return newStack;
}
    private synchronized boolean isrepeatedInStackAndDir(int dir, Texture originTex, Stack<Texture> stackTex, Stack<Integer> stackDir) {

        for (int i = 0; i < stackTex.size(); i++) {
            //if (i == 19)
             //   System.exit(0);
           // System.out.println(i+"dir and stack dir +"+dir+",,,,,"+stackDir.elementAt(i));
           // System.out.println(i+"ii and stack j,j +"+originTex.getI() +"  "+ stackTex.elementAt(i).getI()+",,,,,"+originTex.getJ() +"  "+ stackTex.elementAt(i).getJ());
            if (originTex.getI() == stackTex.elementAt(i).getI()
                    && originTex.getJ() == stackTex.elementAt(i).getJ()
                    && dir == stackDir.elementAt(i)) {
              // System.out.println("repeatable");
                return true;
            }
        }
        return false;

    }

    boolean isrepeadedInStack(Texture desireTex, Stack<Texture> stackTex) {
        for (int i = 0; i < stackTex.size(); i++) {
//             System.out.println(i+"ii and stack j,j +"+desireTex.getI()
//                     +"  "+ stackTex.elementAt(i).getI()+",,,,,"+desireTex.getJ() +"  "+ stackTex.elementAt(i).getJ());
//if (i==12)System.exit(0);
            if (desireTex.getI() == stackTex.elementAt(i).getI()
                    && desireTex.getJ() == stackTex.elementAt(i).getJ()) {
                //System.out.println("repeatable");
                return true;
            }
        }
        return false;
    }

    private boolean isWaySafe(Texture texture,Stack<Texture> stackTex) {
        int maxsize=(Map.width+Map.height)*2;
       // System.out.println(maxsize+"maxsize");
        if (stackTex.size()>maxsize)
            return false;

        if (texture == null)
            return false;
        else
            return true;
    }

    private boolean isDestinationTex(Texture texture, Texture destTex) {
        // System.out.println("dest i , j" + destTex.getI() + destTex.getJ());
        return ((texture.getI() == destTex.getI()) && (texture.getJ() == destTex.getJ()));
    }

    private int[] dirsOfDirOf(int dir) {
        int[] dirs = new int[3];
        if (dir == 1) {
            dirs[0] = 4;
            dirs[1] = 1;
            dirs[2] = 2;
        } else if (dir == 2) {
            dirs[0] = 1;
            dirs[1] = 2;
            dirs[2] = 3;
        } else if (dir == 3) {
            dirs[0] = 2;
            dirs[1] = 3;
            dirs[2] = 4;
        } else if (dir == 4) {
            dirs[0] = 3;
            dirs[1] = 4;
            dirs[2] = 1;
        }
        return dirs;
    }

    private Texture nextOfThisDirThisTex(PlayingPanel pp, int dir, Texture texture) {
        if (texture != null) {
            int itex = texture.getI();
            int jtex = texture.getJ();
            int i = -1;
            int j = -1;
            if (dir == 1) {
                i = itex - 1;
                j = jtex;
            } else if (dir == 2) {
                i = itex;
                j = jtex - 1;
            } else if (dir == 3) {
                i = itex + 1;
                j = jtex;
            } else if (dir == 4) {
                i = itex;
                j = jtex + 1;
            }

            if (i < 0 || j < 0 || j >= Map.height || i >= Map.width)
                return null;
            // System.out.println( "func i,j " + i +"  "+j);
            return pp.textures.elementAt(Texture.indexIJ(i, j));
        } else return null;
    }

    //////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    int kkk = 0;

    @Override
    public Timer move(PlayingPanel PP) {
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getI() + "my  i , j" + getJ() + (PP.destTexture != null));
                if (PP.destTexture != null) {
                    origTex = PP.tileFinder(getX(), getY());

                    System.out.println("in 3");
                    Stack<Stack<Texture>> ssTex = newAlgorithm(PP, 3, origTex);
                    System.out.println("in 3 size " + ssTex.size());
                    System.exit(0);
//                    for (int j = 0; j < ssTex.size(); j++) {
//                        System.out.println(ssTex.elementAt(j).lastElement().getI() +
//                                " last elem of all stacks get << i" + "..............j>>" +
//                                ssTex.elementAt(j).elementAt(0).getJ());
//                        //   if (j==12)System.exit(0);
//                    }
//
//
//                    System.exit(0);
//                    if (ssTex.size() > 0) System.exit(0);
////                    System.out.println("ggggggggggg"+ssTex.size());
//                    else {
//                        System.out.println("in 4");
//                        ssTex = newAlgorithm(PP, 4, origTex);
//                        if (ssTex.size() > 0) System.exit(0);
//                        else {
//                            System.out.println("in 2");
//                            ssTex = newAlgorithm(PP, 2, origTex);
//                            if (ssTex.size() > 0) System.exit(0);
//                            else {
//                                System.out.println("in 1");
//                                ssTex = newAlgorithm(PP, 1, origTex);
//                            }
//                        }

//                    }


                }
            }

        });
        return timer;
    }

    private Image imageForStop(int dir) {
        System.out.println("in stopimage" + dir);
        int k = -1;
        if (dir == 1)
            k = 2;
        else if (dir == 3)
            k = 4;
        else if (dir == 5)
            k = 6;
        else if (dir == 7)
            k = 8;
        else {
        }

        return new ImageIcon("images\\soldier\\" + k + ".png").getImage();
    }

}



