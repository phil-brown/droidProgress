## DroidProgress

---------------------

__droidProgress__ is a [droidQuery](https://github.com/phil-brown/droidQuery) extension for 
showing a progress indicator in *Android*.

To use, add as a *droidQuery* extension:

    try {
        $.extend("progress", "self.philbrown.droidProgress.Progress");
    }
    catch (Throwable t) {
        Log.e("MyApp", "Could not add mail extension");
    }
    
Then get an instance of `Progress`:

    final Progress progress = (Progress) $.with(this).ext("progress", new ProgressOptions().indeterminate(true));
    
To use, call the `start` and `stop` methods. An example use case is for global *Ajax* requests:

    $.ajaxStart(new Function() {
        public void invoke(Object... args) {
            progress.start();
        }
    });
       
    $.ajaxStop(new Function() {
        public void invoke(Object... args) {
            progress.stop();
        }
    });