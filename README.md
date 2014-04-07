FaceDetector
============

# OpenCV installation:

http://docs.opencv.org/doc/tutorials/introduction/linux_install/linux_install.html

sudo apt-get install cmake
sudo apt-get install libgtk2.0-dev

cd ~/<my_working _directory>
git clone https://github.com/Itseez/opencv.git

cd ~/opencv
mkdir release
cd release
cmake -D CMAKE_INSTALL_PREFIX=/usr/local ..

make -j8 -> 8 will be the number of threads of your CPU.
sudo make install

# JVM configuration:

- Remember to add -Djava.library.path=/usr/local/share/OpenCV/java/ in the JVM configuration parameters in order to find static libraries of OpenCV.