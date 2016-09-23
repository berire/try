package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by user on 22.9.2016.
 */
public class NewButton {

    Group thegroup;
    Label textpart;
    ImageButton imagepart;



    public NewButton(Drawable image, java.lang.CharSequence text, Label.LabelStyle style)
    {
        thegroup=new Group();
        textpart=new Label(text,style);
        imagepart=new ImageButton(image);

        thegroup.addActor(textpart);
        thegroup.addActor(imagepart);


    }

    public void setposition(float x,float y)
    {
        imagepart.setPosition(x,y);
        textpart.setPosition((x+imagepart.getWidth()),(y+(imagepart.getHeight()/3)));
    }

    public Group getNewButton()
    {
        return this.thegroup;
    }
}
