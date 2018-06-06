/*
 *  @(#)ModelClipTest.java 1.10 02/10/21 13:45:01
 *
 * Copyright (c) 1996-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed,licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.ModelClip;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * ModelClipTest draws a cylinder and creates two clip planes to see the 
 * interior of the cylinder. 
 */
public class ModelClipTest extends Applet {

    private static final long serialVersionUID = 9087471750590410314L;
    private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {
        // Create the root of the branch graph  
        BranchGroup objRoot = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);

        // Create a Transformgroup to scale all objects so they  
        // appear in the scene.  
        TransformGroup objScale = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.4);
        objScale.setTransform(t3d);
        objRoot.addChild(objScale);

        // This Transformgroup is used by the mouse manipulators to  
        // move the CYlinder.  
        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objScale.addChild(objTrans);

        // Create Model Clip  
        ModelClip mc = new ModelClip();
        boolean enables[] = { false, false, false, false, false, false };
        Vector4d eqn1 = new Vector4d(0.0, 1.0, 0.0, 0.0);
        Vector4d eqn2 = new Vector4d(1.0, 1.0, 0.0, 0.0);
        mc.setEnables(enables);
        mc.setPlane(1, eqn1);
        mc.setPlane(2, eqn2);
        mc.setEnable(1, true);
        mc.setEnable(2, true);
        mc.setInfluencingBounds(bounds);
        objTrans.addChild(mc);

        // Create a cylinder  
        PolygonAttributes attr = new PolygonAttributes();
        attr.setCullFace(PolygonAttributes.CULL_NONE);
        Appearance ap = new Appearance();
        Material mat = new Material();
        mat.setLightingEnable(true);
        ap.setMaterial(mat);
        ap.setPolygonAttributes(attr);

        Cylinder CylinderObj = new Cylinder(1.0f, 2.0f, ap);
        objTrans.addChild(CylinderObj);

        // Create the rotate behavior node  
        MouseRotate behavior = new MouseRotate(objTrans);
        objTrans.addChild(behavior);
        behavior.setSchedulingBounds(bounds);

        // Create the zoom behavior node  
        MouseZoom behavior2 = new MouseZoom(objTrans);
        objTrans.addChild(behavior2);
        behavior2.setSchedulingBounds(bounds);

        // Shine it with two colored lights.  
        Color3f lColor1 = new Color3f(0.5f, 0.0f, 0.5f);
        Color3f lColor2 = new Color3f(0.7f, 0.7f, 0.0f);
        Vector3f lDir1 = new Vector3f(-1.0f, -1.0f, 1.0f);
        Vector3f lDir2 = new Vector3f(0.0f, 0.0f, -1.0f);
        DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
        DirectionalLight lgt2 = new DirectionalLight(lColor2, lDir2);
        lgt1.setInfluencingBounds(bounds);
        lgt2.setInfluencingBounds(bounds);
        objScale.addChild(lgt1);
        objScale.addChild(lgt2);

        // Let Java 3D perform optimizations on this scene graph.  
        objRoot.compile();

        return objRoot;
    }

    public ModelClipTest() {
    }

    public void init() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse
                .getPreferredConfiguration();

        Canvas3D c = new Canvas3D(config);
        add("Center", c);

        // Create a simple scene and attach it to the virtual universe  
        BranchGroup scene = createSceneGraph();
        u = new SimpleUniverse(c);

        // This will move the ViewPlatform back a bit so the  
        // objects in the scene can be viewed.  
        u.getViewingPlatform().setNominalViewingTransform();

        u.addBranchGraph(scene);
    }

    public void destroy() {
        u.cleanup();
    }

    public static void main(String argv[]) {

        new MainFrame(new ModelClipTest(), 500, 500);
    }
}  