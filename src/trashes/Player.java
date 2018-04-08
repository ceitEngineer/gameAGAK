//package trashes;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Vector;
//
///**
// * Created by ABOLFZL on 7/3/2017.
// */
//public class Player extends Motile implements Movable {
//    Vector<Partner> partners = new Vector<>(10);
//    Vector<Texture> texturesInMoving = new Vector<>();
//
//    Player(double x, double y, int i, int j) {
//        super(x, y, i, j);
//        addPartners();
//
//    }
//
//    Player() {
//        addPartners();
//    }
//
//    private void addPartners() {
//        for (int k = 0; k < 10; k++) {
//            partners.add(new Partner());
//        }
//    }
//
//    boolean isTextureNull = false;
//    int ie = 0;
//    boolean isCrashed = true;
//
//
//    private int selectDirection(Texture originTexture, Texture nextDest) {
//        int direction = 0;
//        int iOr = originTexture.getI();
//        int jOr = originTexture.getJ();
//        int iDest = nextDest.getI();
//        int jDest = nextDest.getJ();
//
//        if (iDest == iOr - 1 && jDest == jOr)
//            direction = 1;
//        else if (iDest == iOr - 1 && jDest == jOr - 1)
//            direction = 2;
//        else if (iDest == iOr && jDest == jOr - 1)
//            direction = 3;
//        else if (iDest == iOr + 1 && jDest == jOr - 1)
//            direction = 4;
//        else if (iDest == iOr + 1 && jDest == jOr)
//            direction = 5;
//        else if (iDest == iOr + 1 && jDest == jOr + 1)
//            direction = 6;
//        else if (iDest == iOr && jDest == jOr + 1)
//            direction = 7;
//        else if (iDest == iOr - 1 && jDest == jOr + 1)
//            direction = 8;
//
//        return direction;
//    }
//
//    private int selectDirection(int iOr, int jOr, Texture nextDest) {
//        int direction;
//        int iDest = nextDest.getI();
//        int jDest = nextDest.getJ();
//        System.out.println("ior jor " + iOr + "   " + jOr + "iDest jDest:: " + iDest + "   " + jDest);
//        //  System.exit(0);
//        if (iDest == iOr - 1 && jDest == jOr)
//            direction = 1;
//        else if (iDest == iOr - 1 && jDest == jOr - 1)
//            direction = 2;
//        else if (iDest == iOr && jDest == jOr - 1)
//            direction = 3;
//        else if (iDest == iOr + 1 && jDest == jOr - 1)
//            direction = 4;
//        else if (iDest == iOr + 1 && jDest == jOr)
//            direction = 5;
//        else if (iDest == iOr + 1 && jDest == jOr + 1)
//            direction = 6;
//        else if (iDest == iOr && jDest == jOr + 1)
//            direction = 7;
//        else if (iDest == iOr - 1 && jDest == jOr + 1)
//            direction = 8;
//        else direction = -1;
//
//        return direction;
//    }
//
//    private Image getCorrectDirImage(int direction, String formatImg) {
//        String pathPhoto = "images\\soldier\\";
//        switch (direction) {
//            case 1:
//                pathPhoto += "1." + formatImg;
//                break;
//            case 2:
//                pathPhoto += "2." + formatImg;
//                break;
//            case 3:
//                pathPhoto += "3." + formatImg;
//                break;
//            case 4:
//                pathPhoto += "4." + formatImg;
//                break;
//            case 5:
//                pathPhoto += "5." + formatImg;
//                break;
//            case 6:
//                pathPhoto += "6." + formatImg;
//                break;
//            case 7:
//                pathPhoto += "7." + formatImg;
//                break;
//            case 8:
//                pathPhoto += "8." + formatImg;
//                break;
//            default:
//                System.out.println("Not direction existed");
//                return null;
//        }
//
//        return new ImageIcon(pathPhoto).getImage();
//    }
//
//
//    private boolean seeIsBarrier(Texture texture) {
//        if (texture.getType() != 1)
//            return true;
//        return false;
//    }
//
//
//    private Image getCorrectDirImage(char direction, String formatImg) {
//        String pathPhoto = "images\\player\\";
//        System.out.println("direction " + direction);
//        switch (direction) {
//            case '1':
//                pathPhoto += "1." + formatImg;
//                break;
//            case '2':
//                pathPhoto += "2." + formatImg;
//                break;
//            case '3':
//                pathPhoto += "3." + formatImg;
//                break;
//            case '4':
//                pathPhoto += "4." + formatImg;
//                break;
//            case '5':
//                pathPhoto += "5." + formatImg;
//                break;
//            case '6':
//                pathPhoto += "6." + formatImg;
//                break;
//            case '7':
//                pathPhoto += "7." + formatImg;
//                break;
//            case '8':
//                pathPhoto += "8." + formatImg;
//                break;
//            default:
//                System.out.println("Not direction existed");
//                return null;
//        }
//
//        return new ImageIcon(pathPhoto).getImage();
//    }
//
//
//    public Vector<Texture> trajectoryBest(PlayingPanel PP, double x, double y, double vx, double vy) {
//        double xA = x, yA = y;
//        Texture texture;
//        while (true) {
//            ie++;
//            if (!isTextureNull) {
//                texture = PP.tileFinder(x, y);
//                System.out.println(" tex i" + texture.getI() + " jj " + texture.getJ());
//                isTextureNull = (texture == null);
//                System.out.println(texturesInMoving.size());
//                if (texture != null) {
//                    if (!isCrashed) {
//                        if (texturesInMoving.size() != 0) {
//                            if (!(texturesInMoving.lastElement().getJ() == texture.getJ()
//                                    && texturesInMoving.lastElement().getI() == texture.getI())) {
//                                texturesInMoving.add(texture);
//                                xA += (texturesInMoving.lastElement().getX() + Panel.textureWidth / 2);
//                                yA += (texturesInMoving.lastElement().getY() + Panel.textureHeight / 2);
//                                //System.out.println("exturesInMoving.indexOf(texturesInMoving.lastElement())"+texturesInMoving.indexOf(texturesInMoving.lastElement()));
//                                int i = selectDirection(
//                                        texturesInMoving.elementAt(texturesInMoving.size() - 2).getI(),
//                                        texturesInMoving.elementAt(texturesInMoving.size() - 2).getJ(),
//                                        texturesInMoving.lastElement());
//                                System.out.println("i==" + i);
//                                char c = Character.forDigit(i, 10);
//                                setImage(new ImageIcon("images\\player\\" + i + ".jpg").getImage());
//                                ;
//                            }
//                        } else {
//                            texturesInMoving.add(texture);
//                        }
//
//                    } else {
////                           for (int i = 0; i < texturesInMoving.size(); i++) {
////                               System.out.println(texturesInMoving.get(i).getI()+" j  "+texturesInMoving.get(i).getJ());
////
////                           }
//
//                    }
//
////                        if (Math.abs(xMove - PP.xDest) == 0 && Math.abs(yMove - PP.yDest) == 0) {
//
//
//                    if (texturesInMoving.size() != 0) {
//                        if (texturesInMoving.lastElement().getI() == PP.iDest && texturesInMoving.lastElement().getJ() == PP.jDest) {
//                            // System.exit(0);
//                            //System.out.println("ysssssssssssssssssssssssss");
//                            //                            System.out.println("in if crash");
//                            vx = (0.0);
//                            vy = (0.0);
//                            x += (texturesInMoving.lastElement().getX() + Panel.textureWidth / 2);
//                            y += (texturesInMoving.lastElement().getY() + Panel.textureHeight / 2);
//                            System.out.println("i,j for player:: " + PP.tileFinder(getX(), getY()).getI() + "  " + PP.tileFinder(getX(), getY()).getJ());
//                            isCrashed = true;
//                            return texturesInMoving;
//                        }
//                    }
//                    x = (y + vx);
//                    y = (y + vy);
//                }
//            } else {
//                vx = (0.0);
//                vy = (0.0);
//            }
//            //System.out.println(" vx  vy " + getVx() + getVy());
//        }
//    }
//
//
//    public Texture selectNextTex(PlayingPanel pp, Texture originTex, Texture destTex) {
//        int io = originTex.getI();
//        int jo = originTex.getJ();
//        int ides = destTex.getI();
//        int jdes = destTex.getJ();
//        int inex = io;
//        int jnex = jo;
//        int k = 0;
//
//        if (io < ides)
//            k = 3;
//        else if (io > ides)
//            k = 1;
//        else {
//            if (jo < jdes)
//                k = 4;
//            else
//                k = 2;
//        }
//
//        if (k == 1) {
//            inex = io - 1;
//            jnex = jo;
//        } else if (k == 2) {
//            inex = io;
//            jnex = jo - 1;
//        } else if (k == 3) {
//            inex = io + 1;
//            jnex = jo;
//        } else if (k == 4) {
//            inex = io;
//            jnex = jo + 1;
//        } else {
//        }
//        return pp.textures.elementAt(Texture.indexIJ(inex, jnex));
//    }
//
//    Texture originTex;
//    Texture nexTex0;
//    Texture nexTex;
//    Texture correntTex;
//    Texture beforeTex;
//    boolean isNewVelcity = true;
//    boolean noNeedOrigin = true;
//    boolean isFinished = false;
//    boolean isFirst = true;
//    int dir = 3;
//
//    @Override
//    public Timer move(PlayingPanel PP) {
//        Timer timer = new Timer(5, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isFirst)
//                    beforeTex = PP.textures.lastElement();
//                isFirst = false;
//                correntTex = PP.tileFinder(getX(), getY());
//                //  System.out.println("i, j corrent  " + correntTex.getI() + "  " + correntTex.getJ());
//                if (PP.destTexture != null && PP.destTexture.getType() == 1) {
//
//                    if ( isNewVelcity && !isFinished) {
//                        //System.out.println("in new");
//                        originTex = PP.tileFinder(getX(), getY());
//                        nexTex0 = selectNextTex(PP, originTex, PP.destTexture);
//                        // System.out.println("i, j nextDesire  " + nexTex0.getI() + "  " + nexTex0.getJ());
//                        // System.out.println("i, j befor 2  " + beforeTex.getI() + "  " + beforeTex.getJ());
//                        // System.out.println("i, j corrent  " + correntTex.getI() + "  " + correntTex.getJ());
//                        nexTex = seeManeSoReturnNextTex(PP, correntTex, nexTex0, PP.destTexture);
//                        // System.out.println("i, j next new  " + nexTex.getI() + "  " + nexTex.getJ());
//
//                        double x = nexTex.getX() + Panel.textureWidth / 2;
//                        double y = nexTex.getY() + Panel.textureHeight / 2;
//
//                        double vx = x - getX();
//                        double vy = y - getY();
//
//                        double magnitude = Math.hypot(vx, vy);
//                        vx /= (magnitude / 25);
//                        vy /= (magnitude / 25);
//
//                        setVx(vx);
//                        setVy(vy);
//                        isNewVelcity = false;
//
//                    }
//                    if (!iscrossed) {
//                        beforeTex = correntTex;
//                        iscrossed = true;
//                    }
//                    if (correntTex.getI() == nexTex.getI() && correntTex.getJ() == nexTex.getJ())
//                        if ((Math.abs(getX() - (nexTex.getX() + Panel.textureWidth / 2))) <= 11
//                                &&
//                                (Math.abs(getY() - (nexTex.getY() + Panel.textureHeight / 2))) <= 11) {
//                            // System.out.println("\n\nis crashed\n\n");
//                            setVx(0.0);
//                            setVy(0.0);
//                            setX(correntTex.getX() + Panel.textureWidth / 2);
//                            setY(correntTex.getY() + Panel.textureHeight / 2);
//
//                            iscrossed = false;
//                            isNewVelcity = true;
//                            if (nexTex.getI() == PP.destTexture.getI()
//                                    && nexTex.getJ() == PP.destTexture.getJ()) {
//                                setImage(imageForStop(dir));
//                                isFinished = true;
//                            }
//                        }
////                    if ((Math.abs(getX() - (nexTex.getX() + Panel.textureWidth / 2))) == 0
////                            &&
////                            (Math.abs(getY() - (nexTex.getY() + Panel.textureHeight / 2))) == 0)
////                    {
////                        System.out.println("is crashed");
////                        setVx(0.0);
////                        setVy(0.0);
////
////
////                        ///isNewVelcity = true;
////                      //  noNeedOrigin = false;
////                    }
//
//
//                    // System.out.println("vx , vy for plyer  " + getVx() + "  " + getVy());
//                    setX(getX() + getVx());
//                    setY(getY() + getVy());
//
//                    if (!isFinished){
//                        dir = selectDirection(correntTex, nexTex);
//                        setImage(getCorrectDirImage(dir, "gif"));}
//
//                    System.out.println("x , y for plyer  " + getX() + "  " + getY());
//                    // System.out.println("nex x, nex y for plyer  " +
//                    //  + "  " + Math.abs(getY() - (nexTex.getY() + Panel.textureHeight / 2)));
//
//
//                }
//
//                System.out.println("i, j befor  " + beforeTex.getI() + "  " + beforeTex.getJ());
//
//            }
//        });
//
//
//        return timer;
//    }
//
//    boolean iscrossed = false;
//    boolean beforeIsHere = false;
//
//    private Image imageForStop(int dir) {
//        System.out.println("in stopimage" + dir);
//        int k=-1;
//        if (dir == 1)
//            k = 2;
//        else if (dir == 3)
//            k = 4;
//        else if (dir == 5)
//            k = 6;
//        else if (dir == 7)
//            k = 8;
//        else {}
//
//        return new ImageIcon("images\\soldier\\" + k + ".png").getImage();
//    }
//
//    public Texture seeManeSoReturnNextTex(PlayingPanel pp, Texture beforeTex, Texture nowTex, Texture desireNextTex, Texture finalTex) {
//        if (desireNextTex.getType() != 1) {
//            int ibef = beforeTex.getI();
//            int jbef = beforeTex.getJ();
//            int inow = nowTex.getI();
//            int jnow = nowTex.getJ();
//            int idesire = desireNextTex.getI();
//            int jdesire = desireNextTex.getJ();
//            int ifi = finalTex.getI();
//            int jfi = finalTex.getJ();
//            int itop = inow;
//            int idown = inow;
//            int ileft = inow + 1;
//            int iright = inow - 1;
//            int jtop = jnow - 1;
//            int jleft = jnow;
//            int jdown = jnow + 1;
//            int jright = jnow;
//            int ioffer;
//            int joffer;
//            System.out.println();
//            if ((idesire == ileft && jdesire == jleft) || (idesire == iright && jdesire == jright)) {
//                System.out.println("desire is left or right");
//                if (Math.pow((idown - ifi), (jdown - jfi)) - Math.pow((itop - ifi), (jtop - jfi)) <= 0) {
//
//                    if (pp.textures.elementAt(idown * Map.width + jdown).getType() == 1) {
//                        ioffer = idown;
//                        joffer = jdown;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = itop;
//                        joffer = jtop;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                } else {
//                    if (pp.textures.elementAt(itop * Map.width + jtop).getType() == 1) {
//                        ioffer = itop;
//                        joffer = jtop;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = idown;
//                        joffer = jdown;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//
//                }
//
//            }
//            if ((idesire == itop && jdesire == jtop) || (idesire == idown && jdesire == jdown)) {
//                System.out.println("desire is top or down");
//                if (Math.pow((ileft - ifi), (jleft - jfi)) - Math.pow((iright - ifi), (jright - jfi)) <= 0 &&
//                        !(offerTex(pp, ileft, jleft).getI() == beforeTex.getI()
//                                && offerTex(pp, ileft, jleft).getJ() == beforeTex.getJ())) {
//                    System.out.println("in left <");
//                    if (pp.textures.elementAt(ileft * Map.width + jleft).getType() == 1) {
//                        ioffer = ileft;
//                        joffer = jleft;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = iright;
//                        joffer = jright;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                } else {
//                    System.out.println("in right <");
//                    System.out.println("getType()" + pp.textures.elementAt(iright * Map.width + jright).getType());
//                    if (pp.textures.elementAt(iright * Map.width + jright).getType() == 1) {
//                        System.out.println("mane nist");
//                        ioffer = iright;
//                        joffer = jright;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        System.out.println("mane hast");
//                        ioffer = ileft;
//                        joffer = jleft;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                }
//            }
//
//
//        }
//        return desireNextTex;
//    }
//
//    public Texture seeManeSoReturnNextTex(PlayingPanel pp, Texture nowTex, Texture desireNextTex, Texture finalTex) {
//        if (desireNextTex.getType() != 1) {
//            int inow = nowTex.getI();
//            int jnow = nowTex.getJ();
//            int idesire = desireNextTex.getI();
//            int jdesire = desireNextTex.getJ();
//            int ifi = finalTex.getI();
//            int jfi = finalTex.getJ();
//            int itop = inow;
//            int idown = inow;
//            int ileft = inow + 1;
//            int iright = inow - 1;
//            int jtop = jnow - 1;
//            int jleft = jnow;
//            int jdown = jnow + 1;
//            int jright = jnow;
//            int ioffer;
//            int joffer;
//            System.out.println();
//            if ((idesire == ileft && jdesire == jleft) || (idesire == iright && jdesire == jright)) {
//                // System.out.println("desire is left or right");
//                if (Math.pow((idown - ifi), (jdown - jfi)) - Math.pow((itop - ifi), (jtop - jfi)) <= 0) {
//
//                    if (pp.textures.elementAt(idown * Map.width + jdown).getType() != 1) {
//                        ioffer = idown;
//                        joffer = jdown;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = itop;
//                        joffer = jtop;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                } else {
//                    if (pp.textures.elementAt(itop * Map.width + jtop).getType() != 1) {
//                        ioffer = itop;
//                        joffer = jtop;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = idown;
//                        ;
//                        joffer = jdown;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//
//                }
//
//            }
//            if ((idesire == itop && jdesire == jtop) || (idesire == idown && jdesire == jdown)) {
//                //  System.out.println("desire is top or down");;
//                if (Math.pow((ileft - ifi), (jleft - jfi)) - Math.pow((iright - ifi), (jright - jfi)) <= 0) {
//                    //System.out.println("in left <");;
//                    ;
//                    if (pp.textures.elementAt(ileft * Map.width + jleft).getType() != 1) {
//                        ioffer = ileft;
//                        joffer = jleft;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        ioffer = iright;
//                        joffer = jright;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                } else {
//                    //System.out.println("in right <");
//                    //    System.out.println("getType()"+pp.textures.elementAt(iright * Map.width + jright).getType());
//                    if (pp.textures.elementAt(iright * Map.width + jright).getType() != 1) {
//                        // System.out.println("mane nist");
//                        ioffer = iright;
//                        joffer = jright;
//                        return offerTex(pp, ioffer, joffer);
//                    } else {
//                        //  System.out.println("mane hast");
//                        ioffer = ileft;
//                        joffer = jleft;
//                        return offerTex(pp, ioffer, joffer);
//                    }
//                }
//            }
//
//
//        }
//        return desireNextTex;
//    }
//
//
//    private Texture offerTex(PlayingPanel pp, int ioffer, int joffer) {
//        return pp.textures.elementAt(Texture.indexIJ(ioffer, joffer));
//
//    }
//
//}
//
//
//
