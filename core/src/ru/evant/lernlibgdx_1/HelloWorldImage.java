package ru.evant.lernlibgdx_1;

//	Самообразование #1
//	Java Game Development with LibGDX, 2nd Edition

/*
	Наш первый проект состоит из трех классов.
	Первый класс, называется HelloWorldImage, использует функциональные возможности класса LibGDX с именем Game, расширяя его.
	Второй и третий - это так называемые классы драйвера:
	AndroidLauncher и DesktopLauncher
	Они необходимы для запуска на других платформах:
	AndroidLauncher - запускает приложение на устройствах Android
	DesktopLauncher - запускает приложение на компьютере
	Класс драйвера (также иногда называемый основным, точкой входа, стартером или классом запуска) - это класс,
	предназначенный для управления выполнением другого класса, что часто включает создание экземпляра класса и
	вызов одного или нескольких его методов. Для выполнения этой задачи классу драйверов обычно требуется только один метод;
	этот метод традиционно называется main. Поскольку это первый метод, вызываемый программой, основной(main) метод должен
	быть объявлен как статический, потому что при запуске программы нет доступных экземпляров для запуска методов на основе экземпляров.
	Если бы основной метод не был статичным, у нас была бы проблема, похожая на философскую головоломку: что было первым,
	курица или яйцо? Что-то должно быть в состоянии создать экземпляр класса без его создания, и это именно то,
	что делает статический основной метод класса драйвера.
 */

/*
	Класс HelloWorldImage содержит два объекта: Texture и SpriteBatch.
	Texture - это объект в котором хранятся данные, относящиеся к изображению:
	размеры (ширина и высота) изображения и цвет каждого пикселя.
	SpriteBatch - это объект, который рисует изображения на экране.
	Класс HelloWorldImage также содержит два метода: create и render.
	Метод create инициализирует объекты Texture и SpriteBatch.
	В частности, объект Texture требует файл изображения, из которого он будет получать данные изображения.
	Для этого вы создаете FileHandle - объект LibGDX, который используется для доступа к файлам, хранящимся на компьютере.
	Класс Gdx содержит много полезных статических объектов и методов (аналогично классу Java Math);
	здесь вы используете метод с именем internal для создания FileHandle объекта, который будет использоваться объектом текстуры(Texture).
    Внутренний метод будет искать файл в каталоге проекта, то же место, где хранятся скомпилированные файлы классов.
	После завершения метода создания(create), метод рендеринга(render) будет вызван LibGDX примерно 60 раз в секунду
	(поскольку это то, что класс Game делает по умолчанию).
	Этот метод содержит пару вызовов статических методов:
	один для выбора определенного цвета фона (значения в этом примере соответствуют белому цвету),
	а другой - использовать этот цвет для очистки окна.
	После этого объект SpriteBatch используется для позиционирования и рисования текстуры в окне.
	Поскольку ни текстура, ни координаты в этом примере не меняются, тот факт,
	что метод рендеринга вызывается неоднократно, здесь не имеет значения.
	Однако, если вы должны периодически менять изображение, вы можете сгенерировать анимацию;
	если бы вы постепенно меняли координаты, вы могли бы имитировать движение.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorldImage extends Game {

	private Texture texture;
	// это объект в котором хранятся данные, относящиеся к изображению:
	// размеры (ширина и высота) изображения и цвет каждого пикселя.

	private SpriteBatch batch;
	// это объект, который рисует изображения на экране.

	/*
	Метод create инициализирует объекты Texture и SpriteBatch.
	В частности, объект Texture требует файл изображения, из которого он будет получать данные изображения.
	Для этого вы создаете FileHandle - объект LibGDX, который используется для доступа к файлам, хранящимся на компьютере.
	Класс Gdx содержит много полезных статических объектов и методов (аналогично классу Java Math);
	здесь вы используете метод с именем internal для создания FileHandle объекта, который будет использоваться объектом текстуры(Texture).
    Внутренний метод будет искать файл в каталоге проекта, то же место, где хранятся скомпилированные файлы классов.
	 */
	@Override
	public void create () {
		FileHandle worldFile = Gdx.files.internal("world.png");
		texture = new Texture(worldFile);
		batch = new SpriteBatch();
	}

	/*
	После завершения метода создания(create), метод рендеринга(render) будет вызван LibGDX примерно 60 раз в секунду
	(поскольку это то, что класс Game делает по умолчанию).
	Этот метод содержит пару вызовов статических методов:
	один для выбора определенного цвета фона (значения в этом примере соответствуют белому цвету),
	а другой - использовать этот цвет для очистки окна.
	После этого объект SpriteBatch используется для позиционирования и рисования текстуры в окне.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin(); // начать рисовать
		batch.draw(texture, 120, 40, 400,400);
		// нарисовать картинку по координатам х = 120, у = 40, шириной = 400, высотой = 400
		batch.end(); // закончить рисовать
	}

	/*
	Любые действия по завершению работы выполняются методом с именем dispose.
	Этот метод удаляет созданные ранее объекты и освобождает память устройства.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}