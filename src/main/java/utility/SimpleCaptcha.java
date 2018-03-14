package utility;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.imageio.ImageIO;
import nl.captcha.backgrounds.BackgroundProducer;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.GimpyRenderer;
import nl.captcha.gimpy.RippleGimpyRenderer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

public final class SimpleCaptcha implements Serializable {
	private static final long serialVersionUID = 617511236L;
	public static final String NAME = "simpleCaptcha";
	private Builder _builder;

	private SimpleCaptcha(Builder builder) {
		this._builder = builder;
	}

	public boolean isCorrect(String answer) {
		return answer.equals(this._builder._answer);
	}

	public String getAnswer() {
		return this._builder._answer;
	}

	public BufferedImage getImage() {
		return this._builder._img;
	}

	public Date getTimeStamp() {
		return new Date(this._builder._timeStamp.getTime());
	}

	public String toString() {
		return this._builder.toString();
	}

	public static class Builder implements Serializable {
		private static final long serialVersionUID = 12L;
		private String _answer = "";
		private BufferedImage _img;
		private BufferedImage _bg;
		private Date _timeStamp;
		private boolean _addBorder = false;

		public Builder(int width, int height) {
			this._img = new BufferedImage(width, height, 2);
		}

		public Builder addBackground() {
			return addBackground(new TransparentBackgroundProducer());
		}

		public Builder addBackground(BackgroundProducer bgProd) {
			this._bg = bgProd.getBackground(this._img.getWidth(), this._img.getHeight());

			return this;
		}

		public Builder addText() {
			return addText(new DefaultTextProducer());
		}

		public Builder addText(TextProducer txtProd) {
			return addText(txtProd, new DefaultWordRenderer());
		}

		public Builder addText(WordRenderer wRenderer) {
			return addText(new DefaultTextProducer(), wRenderer);
		}

		public Builder addText(TextProducer txtProd, WordRenderer wRenderer) {
			this._answer += txtProd.getText();
			wRenderer.render(this._answer, this._img);

			return this;
		}
		
		public Builder addText(String code) {
			this._answer += code;
			new DefaultWordRenderer().render(this._answer, this._img);
			return this;
		}

		public Builder addNoise() {
			return addNoise(new CurvedLineNoiseProducer());
		}

		public Builder addNoise(NoiseProducer nProd) {
			nProd.makeNoise(this._img);
			return this;
		}

		public Builder gimp() {
			return gimp(new RippleGimpyRenderer());
		}

		public Builder gimp(GimpyRenderer gimpy) {
			gimpy.gimp(this._img);
			return this;
		}

		public Builder addBorder() {
			this._addBorder = true;

			return this;
		}

		public SimpleCaptcha build() {
			if (this._bg == null) {
				this._bg = new TransparentBackgroundProducer().getBackground(this._img.getWidth(), this._img.getHeight());
			}

			Graphics2D g = this._bg.createGraphics();
			g.setComposite(AlphaComposite.getInstance(3, 1.0F));
			g.drawImage(this._img, null, null);

			if (this._addBorder) {
				int width = this._img.getWidth();
				int height = this._img.getHeight();

				g.setColor(Color.BLACK);
				g.drawLine(0, 0, 0, width);
				g.drawLine(0, 0, width, 0);
				g.drawLine(0, height - 1, width, height - 1);
				g.drawLine(width - 1, height - 1, width - 1, 0);
			}

			this._img = this._bg;

			this._timeStamp = new Date();

			return new SimpleCaptcha(this);
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[Answer: ");
			sb.append(this._answer);
			sb.append("][Timestamp: ");
			sb.append(this._timeStamp);
			sb.append("][Image: ");
			sb.append(this._img);
			sb.append("]");

			return sb.toString();
		}

		private void writeObject(ObjectOutputStream out) throws IOException {
			out.writeObject(this._answer);
			out.writeObject(this._timeStamp);
			ImageIO.write(this._img, "png", ImageIO.createImageOutputStream(out));
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
			this._answer = ((String) in.readObject());
			this._timeStamp = ((Date) in.readObject());
			this._img = ImageIO.read(ImageIO.createImageInputStream(in));
		}
	}
}
