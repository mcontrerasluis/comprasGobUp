/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DependenciaComponentsPage, DependenciaDeleteDialog, DependenciaUpdatePage } from './dependencia.page-object';

const expect = chai.expect;

describe('Dependencia e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let dependenciaUpdatePage: DependenciaUpdatePage;
    let dependenciaComponentsPage: DependenciaComponentsPage;
    let dependenciaDeleteDialog: DependenciaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Dependencias', async () => {
        await navBarPage.goToEntity('dependencia');
        dependenciaComponentsPage = new DependenciaComponentsPage();
        expect(await dependenciaComponentsPage.getTitle()).to.eq('comprasGobUpApp.dependencia.home.title');
    });

    it('should load create Dependencia page', async () => {
        await dependenciaComponentsPage.clickOnCreateButton();
        dependenciaUpdatePage = new DependenciaUpdatePage();
        expect(await dependenciaUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.dependencia.home.createOrEditLabel');
        await dependenciaUpdatePage.cancel();
    });

    it('should create and save Dependencias', async () => {
        const nbButtonsBeforeCreate = await dependenciaComponentsPage.countDeleteButtons();

        await dependenciaComponentsPage.clickOnCreateButton();
        await promise.all([
            dependenciaUpdatePage.setRamoInput('5'),
            dependenciaUpdatePage.setNombreRamoInput('nombreRamo'),
            dependenciaUpdatePage.setUnidadInput('unidad'),
            dependenciaUpdatePage.setNombreUnidadInput('nombreUnidad'),
            dependenciaUpdatePage.setContactoInput('contacto'),
            dependenciaUpdatePage.setCorreoElectronicoInput('correoElectronico'),
            dependenciaUpdatePage.setTelefonoInput('telefono'),
            dependenciaUpdatePage.userSelectLastOption()
        ]);
        expect(await dependenciaUpdatePage.getRamoInput()).to.eq('5');
        expect(await dependenciaUpdatePage.getNombreRamoInput()).to.eq('nombreRamo');
        expect(await dependenciaUpdatePage.getUnidadInput()).to.eq('unidad');
        expect(await dependenciaUpdatePage.getNombreUnidadInput()).to.eq('nombreUnidad');
        expect(await dependenciaUpdatePage.getContactoInput()).to.eq('contacto');
        expect(await dependenciaUpdatePage.getCorreoElectronicoInput()).to.eq('correoElectronico');
        expect(await dependenciaUpdatePage.getTelefonoInput()).to.eq('telefono');
        await dependenciaUpdatePage.save();
        expect(await dependenciaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await dependenciaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Dependencia', async () => {
        const nbButtonsBeforeDelete = await dependenciaComponentsPage.countDeleteButtons();
        await dependenciaComponentsPage.clickOnLastDeleteButton();

        dependenciaDeleteDialog = new DependenciaDeleteDialog();
        expect(await dependenciaDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.dependencia.delete.question');
        await dependenciaDeleteDialog.clickOnConfirmButton();

        expect(await dependenciaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
